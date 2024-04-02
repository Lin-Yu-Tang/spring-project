package com.tom.entity;

import java.util.Date;



import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="product")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Product {

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
//    @CreationTimestamp
    @CreatedDate
    private Date createTime;
    
//    @Transient
    @Column(name = "CREATOR")
    @CreatedBy
    private String creator;
    
//    @Transient
    @Column(name = "MODIFIER")
    @LastModifiedBy
    private String modifier;

//    @Transient
    @Column(name = "LASTUPDATE")
//    @UpdateTimestamp
    @LastModifiedDate
    private Date lastUpdated;

    
}
