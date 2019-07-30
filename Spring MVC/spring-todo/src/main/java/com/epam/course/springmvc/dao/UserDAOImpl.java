
package com.epam.course.springmvc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.epam.course.springmvc.model.User;
import com.epam.course.springmvc.rowmapper.UserRowMapper;


/**
 * 
 * @author Shashanker_Vaduka
 *
 */
@Repository
public class UserDAOImpl extends BaseDAO implements UserDAO{

    public void save(User u) {
    	findAll();
       String sql ="INSERT INTO USERS(username,password)"
               +"VALUES(:username,:password)";
       Map m = new HashMap();
       m.put("username", u.getUsername());
       m.put("password", u.getPassword());
       
       // KeyHolder kh = new GeneratedKeyHolder(); // Ths object holds Auto incrimenting value like userId after inserting data
        SqlParameterSource ps = new MapSqlParameterSource(m); // This object shows from where The parameter of named parameter is obtained
        super.getNamedParameterJdbcTemplate().update(sql, ps);
    }

    public void update(User u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void findAll() {
    	  String sql = "SELECT id,username,password FROM users ";
          try{
             List<User> users = super.getNamedParameterJdbcTemplate().query(sql, new UserRowMapper());
             for(User u : users) {
            	 System.out.println("Username = "+u.getUsername());
            	 System.out.println("Password = "+u.getPassword());
             }
          } catch(EmptyResultDataAccessException ex)
          {
          }
          
    }

	public User find(User u) {
		// TODO Auto-generated method stub
		User u1 = null;
		String sql = "SELECT *  FROM users WHERE username=:ln AND password=:pw";
        Map m = new HashMap();
        m.put("ln", u.getUsername());
        m.put("pw", u.getPassword());
        try{
            u1 = getNamedParameterJdbcTemplate().queryForObject(sql, m, new UserRowMapper());
            return u1;
        } catch(EmptyResultDataAccessException ex)
        {
        return null;
        }
		
	}
}
