package com.tom;

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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="product")
@Getter
@Setter
public class Product extends BaseEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PID")
    private Long pid;

    @Column(name = "NAME")
    private String name;
    
    @Column(name = "PRICE")
    private int price;

    @Column(name = "IS_DELETED")
    private boolean isDeleted;
    
    @Column(name = "VERSION")
    private Integer version;
    
    public Product() {
    	System.out.println("product init");
    }
    
	@PostConstruct
	public void init() {
		System.out.println("product post construct");
	}
	
	@PreDestroy
	public void end() {
		System.out.println("product destroy");
	}
    
}
