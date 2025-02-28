package com.tom;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DataAccessLayer {
	
	@Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public <T> List<T> queryForNamedParams(String sql, Map<String, Object> params, Class<T> clazz) {
        return namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(clazz));
    }
	
	public int update(String sql, Map<String, Object> params) {
        return namedParameterJdbcTemplate.update(sql, params);
    }
	
	
	@SuppressWarnings("deprecation")
	public <T> List<T> query(String sql, List<Object> params, Class<T> clazz) {
		Object[] array = params.toArray();
		return jdbcTemplate.query(sql, array, new BeanPropertyRowMapper<>(clazz));
    }
	
	public <T> List<T> query(String sql, Class<T> clazz) {
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(clazz));
    }
	

}
