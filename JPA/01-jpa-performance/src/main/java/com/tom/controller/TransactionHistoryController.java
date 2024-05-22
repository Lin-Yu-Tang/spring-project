package com.tom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tom.bean.PageRequestDTO;
import com.tom.service.TransactionHistoryService;

@RestController
public class TransactionHistoryController {

	@Autowired
	private TransactionHistoryService transactionHistoryService;
	
	@PostMapping("trans")
	public String findAll(@RequestBody PageRequestDTO pageRequest) {
		PageRequest pageable = PageRequest.of(pageRequest.page() - 1 , pageRequest.size());
		transactionHistoryService.findAllByPage(pageable);
		
		return "";
	}
	
	
	
}
