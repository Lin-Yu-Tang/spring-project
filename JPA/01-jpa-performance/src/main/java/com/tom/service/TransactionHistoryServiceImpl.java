package com.tom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
		Page<TransactionHistory> all = transactionHistoryRepository.findAll(pageable);
		return all;
	}

	@Override
	public CursorPage<TransactionHistory> findAllByJDBCPage(Pageable pageable) {
		CursorPage<TransactionHistory> data = transactionHistoryDaoRepository.queryByOffsetPageable(pageable);
		return data;
	}
	
	@Override
	public CursorPage<TransactionHistory> findAllByJDBCPageAndSort(Pageable pageable) {
		CursorPage<TransactionHistory> data = transactionHistoryDaoRepository.queryByOffsetPageableAndSort(pageable);
		return data;
	}

	@Override
	public CursorPage<TransactionHistory> findAllByCursorPageAndSort(Pageable pageable, Integer nextCursor) {
        CursorPage<TransactionHistory> data = transactionHistoryDaoRepository.queryByCursorPageableAndSort(pageable, nextCursor);
		return data;
	}
	
}
