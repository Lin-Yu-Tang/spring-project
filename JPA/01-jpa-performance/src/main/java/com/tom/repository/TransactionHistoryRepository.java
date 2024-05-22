package com.tom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tom.entity.TransactionHistory;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long>{
	
	
}
