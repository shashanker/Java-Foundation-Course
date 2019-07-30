/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.course.springmvc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.epam.course.springmvc.model.Todo;

/**
 * 
 * @author Shashanker_Vaduka
 *
 */
public class TodoRowMapper implements RowMapper<Todo>{

    public Todo mapRow(ResultSet rs, int i) throws SQLException {
       Todo t=new Todo();
       t.setTodoId(rs.getLong("todoId"));
       t.setUserId(rs.getLong("userId"));
       t.setMonth(rs.getString("month"));
       t.setDay(rs.getString("day"));
       t.setYear(rs.getString("year"));
       t.setTitle(rs.getString("title"));
       t.setDescription(rs.getString("description"));
       t.setPriority(rs.getString("priority"));
       return t;
       
    }
    
}
