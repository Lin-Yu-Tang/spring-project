package com.tom;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MultiDatasourceService {
	
	@Autowired
	private JdbcTemplate mysqlJdbcTemplate;
	@Autowired
    private JdbcTemplate postgresJdbcTemplate;
	
	public MultiDatasourceService(@Qualifier("mysqlJdbcTemplate") JdbcTemplate mysqlJdbcTemplate,
            @Qualifier("postgresJdbcTemplate") JdbcTemplate postgresJdbcTemplate) {
		this.mysqlJdbcTemplate = mysqlJdbcTemplate;
        this.postgresJdbcTemplate = postgresJdbcTemplate;
	}
	
    public void testMultiDataSource() {
        // 操作 MySQL
        String sql = "SELECT * FROM PRODUCT";
        List<Product> product = mysqlJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));

        // 操作 PostgreSQL
        String postgresQuery = "SELECT * FROM employee";
        List<Employee> employee = postgresJdbcTemplate.query(postgresQuery, new BeanPropertyRowMapper<>(Employee.class));
        
        System.out.println(product);
        System.out.println(employee);
    }
    
    @Transactional(transactionManager = "chainedTransactionManager")
    public void testTransaction() throws Exception {
    	
    	String productSql = """
    				UPDATE PRODUCT
    				SET 
    					VERSION = VERSION + 1
    				WHERE PID = 1
    			""";
    	mysqlJdbcTemplate.update(productSql);
    	
    	
    	String employeeSql = """
    				UPDATE EMPLOYEE
    				SET 
    					SALARY = SALARY + 1
    				WHERE ID = 1
    			""";
    	postgresJdbcTemplate.update(employeeSql);
    	if (true) {
//    		throw new Exception("eee");
    		throw new NullPointerException();
    	}
		
    }
    
    
    
    
    
    
    
    
    
    
    
}
