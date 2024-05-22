package com.tom.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tom.entity.TransactionHistory;

public interface TransactionHistoryService {

	Page<TransactionHistory> findAllByPage(Pageable pageable);
	
	Page<TransactionHistory> findAllByJDBCPage(Pageable pageable);
	
}
