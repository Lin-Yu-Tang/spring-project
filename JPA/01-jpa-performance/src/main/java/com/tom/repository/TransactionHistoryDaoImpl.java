package com.tom.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.tom.bean.CursorPage;
import com.tom.bean.CursorPageImpl;
import com.tom.entity.TransactionHistory;

@Service
public class TransactionHistoryDaoImpl implements TransactionHistoryDaoRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public CursorPage<TransactionHistory> queryByOffsetPageable(Pageable pageable) {
		int pageSize = pageable.getPageSize();
		int pageNumber = pageable.getPageNumber();
		
		MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("START", pageNumber);
        params.addValue("END", pageNumber + pageSize);
        params.addValue("SIZE", pageSize);
        
        List<TransactionHistory> all = jdbcTemplate.query(offsetPagingQuery(), params,
				new BeanPropertyRowMapper<>(TransactionHistory.class));
		
		Integer counter = jdbcTemplate.queryForObject(countQuery(), params, Integer.class);
        
		return new CursorPageImpl<TransactionHistory>(all, pageable, counter);
	}

	private String countQuery() {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT count_big(0) FROM bigTransactionHistory ");
		
		return sb.toString();
	}
	
	private String offsetPagingQuery() {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM bigTransactionHistory ");
		sb.append("ORDER BY (SELECT 0) OFFSET :START ROWS FETCH FIRST :SIZE ROWS ONLY ");
		
		return sb.toString();
	}
	
	private String subPagingQuery() {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM ( ");
		sb.append("SELECT *, ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) as row FROM bigTransactionHistory ");
		sb.append(") a WHERE a.row > :START and a.row <= :END ");
		
		return sb.toString();
	}

	@Override
	public CursorPage<TransactionHistory> queryByOffsetPageableAndSort(Pageable pageable) {
		int pageSize = pageable.getPageSize();
		int pageNumber = pageable.getPageNumber();
		
		MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("START", pageNumber);
        params.addValue("END", pageNumber + pageSize);
        params.addValue("SIZE", pageSize);
        
		List<TransactionHistory> all = jdbcTemplate.query(offsetPagingAndSortQuery(), params,
				new BeanPropertyRowMapper<>(TransactionHistory.class));
		
		Integer counter = jdbcTemplate.queryForObject(countQuery(), params, Integer.class);
		
		return new CursorPageImpl<TransactionHistory>(all, pageable, counter);
	}
	
	private String subPagingAndSortQuery() {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM ( ");
		sb.append("SELECT *, ROW_NUMBER() OVER (ORDER BY TransactionID) as row FROM bigTransactionHistory ");
		sb.append(") a WHERE a.row > :START and a.row <= :END ");
		
		return sb.toString();
	}
	
	private String offsetPagingAndSortQuery() {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM bigTransactionHistory ");
		sb.append("ORDER BY TransactionID OFFSET :START ROWS FETCH FIRST :SIZE ROWS ONLY ");
		
		return sb.toString();
	}
	
	@Override
	public CursorPage<TransactionHistory> queryByCursorPageableAndSort(Pageable pageable, Integer nextCursor) {
		int pageSize = pageable.getPageSize();
		int pageNumber = pageable.getPageNumber();
		
		MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("SIZE", pageSize);
        params.addValue("CURSOR_ID", nextCursor);
        
		List<TransactionHistory> all = jdbcTemplate.query(cursorPagingAndSortQuery(pageNumber == 0), params,
				new BeanPropertyRowMapper<>(TransactionHistory.class));
		
		Integer counter = jdbcTemplate.queryForObject(countQuery(), params, Integer.class);
		
		return new CursorPageImpl<TransactionHistory>(all, pageable, counter);
	}
	
	private String cursorPagingAndSortQuery(boolean isFirstPage) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT TOP (:SIZE) * FROM bigTransactionHistory ");
		sb.append(isFirstPage ? "" : "WHERE TransactionID > :CURSOR_ID ");
		sb.append("ORDER BY TransactionID ");
		
		return sb.toString();
	}

	
	
	
}
