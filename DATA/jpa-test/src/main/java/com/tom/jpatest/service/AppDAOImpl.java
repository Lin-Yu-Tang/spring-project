package com.tom.jpatest.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.mapping.Map;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AppDAOImpl implements AppDAO {

	@Autowired
	private EntityManager em;
	
	@Autowired
	private JdbcTemplate template;
	
	
	// TODO
	@Transactional
	public List executeQuery(String queryString) {
		Query query = em.createNativeQuery(queryString);
		return query.getResultList();
	}

	@SuppressWarnings("deprecation")
	@Override
	public List findByPage(int page) {
		Query query = em.createNativeQuery("SELECT * FROM PRODUCT");
//		query.unwrap(null);
//		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//		NativeQuery unwrap = query.unwrap(NativeQuery.class);
		
		/**
		 * setResultTransformer
		 * @deprecated (since 5.2)
		 * @todo develop a new approach to result transformers
		 */
		query.unwrap(NativeQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setFirstResult(0);
		query.setMaxResults(10);
		List resultList = query.getResultList();
		return resultList;
	}
	
	
	@Override
	public List findByPage(Pageable pageable) {
		Query query = em.createNativeQuery("SELECT * FROM PRODUCT");
		
		
		Pageable p = pageable;
		int pageNumber = p.getPageNumber();
		int pageSize = p.getPageSize();
		
		System.out.println("page number: " + pageNumber);
		System.out.println("size: " + pageSize);
		
		/**
		 * @deprecated (since 5.2)
		 * @todo develop a new approach to result transformers
		 */
		query.unwrap(NativeQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setFirstResult(0);
		query.setMaxResults(10);
		List resultList = query.getResultList();
		return resultList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
