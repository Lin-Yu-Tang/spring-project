package com.tom.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.google.common.base.Stopwatch;
import com.tom.entity.TransactionHistory;
import com.tom.repository.TransactionHistoryRepository;


@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {
	
	@Autowired
	private TransactionHistoryRepository transactionHistoryRepository;
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public Page<TransactionHistory> findAllByPage(Pageable pageable) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		Page<TransactionHistory> all = transactionHistoryRepository.findAll(pageable);
		stopwatch.stop();
		System.out.println("::::: JPA :::::");
		measureTimeElapsed(stopwatch.elapsed(TimeUnit.NANOSECONDS));
		
		return all;
	}

	@Override
	public Page<TransactionHistory> findAllByJDBCPage(Pageable pageable) {
		Stopwatch stopwatch = Stopwatch.createStarted();

		int pageSize = pageable.getPageSize();
		int pageNumber = pageable.getPageNumber();
		
		MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("START", pageNumber);
        params.addValue("END", pageNumber + pageSize);
        params.addValue("SIZE", pageSize);
        
		List<TransactionHistory> all = jdbcTemplate.query(jdbcQuery2(), params,
				new BeanPropertyRowMapper<>(TransactionHistory.class));
		
		
		Integer counter = jdbcTemplate.queryForObject(countQuery(), params, Integer.class);
		System.out.println("count: " + counter);
		
		stopwatch.stop();
		System.out.println("::::: JDBC :::::");
		measureTimeElapsed(stopwatch.elapsed(TimeUnit.NANOSECONDS));
		
//		all.forEach(System.out::println);
		
		return new PageImpl<TransactionHistory>(all);
	}
	
	private void measureTimeElapsed(long elapsed) {
		System.out.println("NANOSECONDS: " + elapsed + "ns");
		System.out.println("SECONDS: " + TimeUnit.MILLISECONDS.convert(elapsed, TimeUnit.NANOSECONDS) +"ms");
	}
	
	private String jdbcQuery1() {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM ( ");
		sb.append("SELECT *, ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) as row FROM bigTransactionHistory ");
		sb.append(") a WHERE a.row > :START and a.row <= :END ");
		
		return sb.toString();
	}
	
	private String jdbcQuery2() {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM bigTransactionHistory ");
		sb.append("ORDER BY (SELECT 0) OFFSET :START ROWS FETCH FIRST :SIZE ROWS ONLY ");
		
		return sb.toString();
	}
	
	private String countQuery() {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT count_big(0) FROM bigTransactionHistory ");
		
		return sb.toString();
	}

	@Override
	public Page<TransactionHistory> findAllByJDBCPageAndSort(Pageable pageable) {
		Stopwatch stopwatch = Stopwatch.createStarted();

		int pageSize = pageable.getPageSize();
		int pageNumber = pageable.getPageNumber();
		
		MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("START", pageNumber);
        params.addValue("END", pageNumber + pageSize);
        params.addValue("SIZE", pageSize);
        
		List<TransactionHistory> all = jdbcTemplate.query(jdbcQuery4(), params,
				new BeanPropertyRowMapper<>(TransactionHistory.class));
		
		
		Integer counter = jdbcTemplate.queryForObject(countQuery(), params, Integer.class);
		System.out.println("count: " + counter);
		
		stopwatch.stop();
		System.out.println("::::: JDBC SORT :::::");
		measureTimeElapsed(stopwatch.elapsed(TimeUnit.NANOSECONDS));
		
//		all.forEach(System.out::println);
		
		return new PageImpl<TransactionHistory>(all);
	}
	
	private String jdbcQuery3() {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM ( ");
		sb.append("SELECT *, ROW_NUMBER() OVER (ORDER BY TransactionID) as row FROM bigTransactionHistory ");
		sb.append(") a WHERE a.row > :START and a.row <= :END ");
		
		return sb.toString();
	}
	
	private String jdbcQuery4() {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM bigTransactionHistory ");
		sb.append("ORDER BY TransactionID OFFSET :START ROWS FETCH FIRST :SIZE ROWS ONLY ");
		
		return sb.toString();
	}

	@Override
	public Page<TransactionHistory> findAllByCursorPageAndSort(Pageable pageable, Integer nextCursor) {
		Stopwatch stopwatch = Stopwatch.createStarted();

		int pageSize = pageable.getPageSize();
		int pageNumber = pageable.getPageNumber();
		
		MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("SIZE", pageSize);
        params.addValue("CURSOR_ID", nextCursor);
        
		List<TransactionHistory> all = jdbcTemplate.query(jdbcQuery5(pageNumber == 0), params,
				new BeanPropertyRowMapper<>(TransactionHistory.class));
		
		Integer counter = jdbcTemplate.queryForObject(countQuery(), params, Integer.class);
		System.out.println("count: " + counter);
		
		stopwatch.stop();
		System.out.println("::::: CURSOR SORT :::::");
		measureTimeElapsed(stopwatch.elapsed(TimeUnit.NANOSECONDS));
		
//		all.forEach(System.out::println);
		
		return new PageImpl<TransactionHistory>(all);
	}
	
	private String jdbcQuery5(boolean isFirstPage) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT TOP (:SIZE) * FROM bigTransactionHistory ");
		sb.append(isFirstPage ? "" : "WHERE TransactionID > :CURSOR_ID ");
		sb.append("ORDER BY TransactionID ");
		
		return sb.toString();
	}
}
