package com.tom.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
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

}