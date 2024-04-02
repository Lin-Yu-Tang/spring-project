package com.tom;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

//  @Transient
	@Column(name = "CREATETIME")
//  @CreationTimestamp
	@CreatedDate
	private Date createTime;

//  @Transient
	@Column(name = "CREATOR")
	@CreatedBy
	private String creator;

//  @Transient
	@Column(name = "MODIFIER")
	@LastModifiedBy
	private String modifier;

//  @Transient
	@Column(name = "LASTUPDATE")
//  @UpdateTimestamp
	@LastModifiedDate
	private Date lastUpdated;
	
	@Transient
	@Autowired
	private UserInfo userInfo;
	
//	@Transient
//	@Autowired
//	private SomeComponent com;
	
	public BaseEntity() {
		System.out.println("base entity init");
		System.out.println(userInfo);
		
//		System.out.println("com: " + com.getSession());
	}
	
	@PostConstruct
	public void init() {
		System.out.println("base entity post construct");
	}
	
	@PreDestroy
	public void end() {
		System.out.println("base entity destroy");
	}

}
