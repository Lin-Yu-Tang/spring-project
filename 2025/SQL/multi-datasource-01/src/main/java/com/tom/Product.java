package com.tom;

import java.util.Date;

import lombok.Data;

@Data
public class Product {
	
	private int pid;
	private String name;
	private int price;
	private int isDeleted;
	private int version;
	private Date createTime;
	private String creator;
	private String modifyier;
	private Date lastUpdate;
	
	@Data
	static class Product2 {
		
		private int num;
		private String productName;
		private int price;
		
	}
	
}
