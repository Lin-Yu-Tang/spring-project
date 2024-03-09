package com.tom.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="product")
@Data
public class Product implements Serializable {

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