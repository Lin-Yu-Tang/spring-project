package com.tom.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
		measureTimeElapsed(stopwatch.elapsed(TimeUnit.NANOSECONDS));
		
		return all;
	}

	@Override
	public Page<TransactionHistory> findAllByJDBCPage(Pageable pageable) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		
		StringBuffer sb = new StringBuffer();
		// TODO
		
		List<TransactionHistory> all = jdbcTemplate.query(sb.toString(), new BeanPropertyRowMapper<>(TransactionHistory.class));
		stopwatch.stop();
		measureTimeElapsed(stopwatch.elapsed(TimeUnit.NANOSECONDS));
		
		return null;
	}
	
	private void measureTimeElapsed(long elapsed) {
		System.out.println("NANOSECONDS: " + elapsed + "ns");
		System.out.println("SECONDS: " + TimeUnit.MILLISECONDS.convert(elapsed, TimeUnit.NANOSECONDS) +"ms");
	}
	
	
	
}
