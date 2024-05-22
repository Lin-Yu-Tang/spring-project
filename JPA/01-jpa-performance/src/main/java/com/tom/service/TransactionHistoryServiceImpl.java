package com.tom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tom.entity.TransactionHistory;
import com.tom.repository.TransactionHistoryRepository;


@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {
	
	@Autowired
	private TransactionHistoryRepository transactionHistoryRepository;

	@Override
	public Page<TransactionHistory> findAllByPage(Pageable pageable) {
		
		Page<TransactionHistory> all = transactionHistoryRepository.findAll(pageable);
		
		return all;
	}
	
	
	
}
