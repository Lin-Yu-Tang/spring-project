package com.tom.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.base.Stopwatch;
import com.tom.bean.CursorPage;
import com.tom.entity.TransactionHistory;
import com.tom.repository.TransactionHistoryDaoRepository;
import com.tom.repository.TransactionHistoryRepository;


@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {
	
	@Autowired
	private TransactionHistoryRepository transactionHistoryRepository;
	
	@Autowired
	private TransactionHistoryDaoRepository transactionHistoryDaoRepository;
	
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
	public CursorPage<TransactionHistory> findAllByJDBCPage(Pageable pageable) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		
		CursorPage<TransactionHistory> data = transactionHistoryDaoRepository.queryByOffsetPageable(pageable);
		
		stopwatch.stop();
		
		System.out.println("::::: JDBC :::::");
		measureTimeElapsed(stopwatch.elapsed(TimeUnit.NANOSECONDS));
		
		System.out.println("count: " + data.getTotal());
		data.getContent().forEach(System.out::println);
		
		return data;
	}
	
	@Override
	public CursorPage<TransactionHistory> findAllByJDBCPageAndSort(Pageable pageable) {
		Stopwatch stopwatch = Stopwatch.createStarted();

		CursorPage<TransactionHistory> data = transactionHistoryDaoRepository.queryByOffsetPageableAndSort(pageable);
		
		stopwatch.stop();
		
		System.out.println("::::: JDBC SORT :::::");
		measureTimeElapsed(stopwatch.elapsed(TimeUnit.NANOSECONDS));
		
		System.out.println("count: " + data.getTotal());
		data.getContent().forEach(System.out::println);
		
		return data;
	}

	@Override
	public CursorPage<TransactionHistory> findAllByCursorPageAndSort(Pageable pageable, Integer nextCursor) {
		Stopwatch stopwatch = Stopwatch.createStarted();

        CursorPage<TransactionHistory> data = transactionHistoryDaoRepository.queryByCursorPageableAndSort(pageable, nextCursor);
		
		stopwatch.stop();
		System.out.println("::::: CURSOR SORT :::::");
		measureTimeElapsed(stopwatch.elapsed(TimeUnit.NANOSECONDS));
		
		System.out.println("count: " + data.getTotal());
		data.getContent().forEach(System.out::println);
		
		return data;
	}
	
	private void measureTimeElapsed(long elapsed) {
		System.out.println("NANOSECONDS: " + elapsed + "ns");
		System.out.println("SECONDS: " + TimeUnit.MILLISECONDS.convert(elapsed, TimeUnit.NANOSECONDS) +"ms");
	}
}
