package com.tom.jpatest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="product")
@Data
public class Product extends SoftDeleteEntity<Long, Product> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PID")
    private Long pid;

    @Column(name = "NAME")
    private String name;
    
    @Column(name = "PRICE")
    private int price;

//    @Column(name = "date_created")
//    @CreationTimestamp
//    private Date dateCreated;
//
//    @Column(name = "last_updated")
//    @UpdateTimestamp
//    private Date lastUpdated;
}

