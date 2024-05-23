package com.tom.repository;

import org.springframework.data.domain.Pageable;

import com.tom.bean.CursorPage;
import com.tom.entity.TransactionHistory;

public interface TransactionHistoryDaoRepository {

	CursorPage<TransactionHistory> queryByOffsetPageable(Pageable pageable);
	
	CursorPage<TransactionHistory> queryByOffsetPageableAndSort(Pageable pageable);
	
	CursorPage<TransactionHistory> queryByCursorPageableAndSort(Pageable pageable, Integer nextCursor);
}
