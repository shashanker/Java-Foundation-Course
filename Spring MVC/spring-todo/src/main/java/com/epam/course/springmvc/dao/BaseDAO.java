
package com.epam.course.springmvc.dao;


import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * 
 * @author Shashanker_Vaduka
 *
 */
// Do not use @Repositry or @Service Or @Component
// Because This class will not indipendently exist in the Spring IOC
 abstract public class BaseDAO extends NamedParameterJdbcDaoSupport{
	 @Autowired
	    public void setDataSource2(DataSource ds){
	         super.setDataSource(ds);
	    }
    
}
