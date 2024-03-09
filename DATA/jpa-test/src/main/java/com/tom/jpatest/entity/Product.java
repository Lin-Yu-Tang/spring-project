package com.tom.jpatest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="product")
@EqualsAndHashCode(of = { "pid" }, callSuper = false)
@Data
public class Product extends SoftDeleteEntity<Long, Product> implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PID")
    private Long pid;

    @Column(name = "NAME")
    private String name;
    
    @Column(name = "PRICE")
    private int price;

//    @Transient
    @Column(name = "CREATETIME")
    @CreationTimestamp
    private Date createTime;
    
//    @Transient
    @Column(name = "CREATOR")
    private String creator;
    
//    @Transient
    @Column(name = "MODIFIER")
    private String modifier;

//    @Transient
    @Column(name = "LASTUPDATE")
    @UpdateTimestamp
    private Date lastUpdated;
}

