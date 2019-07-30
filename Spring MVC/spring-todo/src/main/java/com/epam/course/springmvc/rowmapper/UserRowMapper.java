/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.course.springmvc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.epam.course.springmvc.model.User;

/**
 * 
 * @author Shashanker_Vaduka
 *
 */
public class UserRowMapper implements RowMapper<User> {

    public User mapRow(ResultSet rs, int i) throws SQLException {
        User u = new User();
        
        u.setId(rs.getLong("Id"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
       
        return u;
    }

}
