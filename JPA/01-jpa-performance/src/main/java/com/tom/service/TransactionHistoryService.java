package com.tom.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tom.bean.CursorPage;
import com.tom.entity.TransactionHistory;

public interface TransactionHistoryService {

	Page<TransactionHistory> findAllByPage(Pageable pageable);
	
	CursorPage<TransactionHistory> findAllByJDBCPage(Pageable pageable);
	
	CursorPage<TransactionHistory> findAllByJDBCPageAndSort(Pageable pageable);
	
	CursorPage<TransactionHistory> findAllByCursorPageAndSort(Pageable pageable, Integer nextCursor);
	
}
