package com.tom.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class JdbcDao {
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public void query(String query, MapSqlParameterSource params, Pageable pageable) {
		
		StringBuilder q = new StringBuilder();
		q.append("WITH T AS ( ");
		q.append(query);
		q.append(") ");
		q.append("SELECT * FROM T ");
		q.append("OFFSET : ");
		
		
		
	}

}
