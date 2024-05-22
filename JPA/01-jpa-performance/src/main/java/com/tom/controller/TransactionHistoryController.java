package com.tom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
	
	@PostMapping("jpa")
	public void findAllByJPA(@RequestBody PageRequestDTO pageRequest) {
		PageRequest pageable = PageRequest.of(pageRequest.page() - 1 , pageRequest.size());
		transactionHistoryService.findAllByPage(pageable);
	}
	
	@PostMapping("jdbc")
	public void findAllByJDBC(@RequestBody PageRequestDTO pageRequest) {
		PageRequest pageable = PageRequest.of(pageRequest.page() - 1 , pageRequest.size());
		transactionHistoryService.findAllByJDBCPage(pageable);
	}
	
	@PostMapping("sort/jpa")
	public void findAllByJPASorted(@RequestBody PageRequestDTO pageRequest) {
		PageRequest pageable = PageRequest.of(pageRequest.page() - 1 , pageRequest.size(), Sort.by("transactionId"));
		transactionHistoryService.findAllByPage(pageable);
	}
	
	@PostMapping("sort/jdbc")
	public void findAllByJDBCSorted(@RequestBody PageRequestDTO pageRequest) {
		PageRequest pageable = PageRequest.of(pageRequest.page() - 1 , pageRequest.size());
		transactionHistoryService.findAllByJDBCPageAndSort(pageable);
	}
	
	@PostMapping("sort/cursor")
	public void findAllByCursorSorted(@RequestBody PageRequestDTO pageRequest) {
		PageRequest pageable = PageRequest.of(pageRequest.page() - 1 , pageRequest.size());
		transactionHistoryService.findAllByCursorPageAndSort(pageable, pageRequest.nextCursor());
	}
	
}
