package com.tom.entity;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "bigTransactionHistory")
@Data
public class TransactionHistory {

	@Id
	@Column(name = "TransactionID")
	private Long transactionId;
	
	@Column(name = "ProductID")
	private String productId;
	
	@Column(name = "TransactionDate")
	private Date transactionDate;
	
	@Column(name = "Quantity")
	private BigDecimal quantity;
	
	@Column(name = "ActualCost")
	private BigDecimal actualCost;
	
}
