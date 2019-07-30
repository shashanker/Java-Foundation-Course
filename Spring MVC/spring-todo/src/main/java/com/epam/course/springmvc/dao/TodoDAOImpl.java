/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.course.springmvc.dao;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.epam.course.springmvc.model.Todo;
import com.epam.course.springmvc.rowmapper.TodoRowMapper;

/**
 * 
 * @author Shashanker_Vaduka
 *
 */
@Repository
public class TodoDAOImpl extends BaseDAO implements TodoDAO {

    public void save(Todo todo) {
        String sql = "INSERT INTO todos (userId,month,day,year,title,description,priority) VALUES (:userId, :month, :day, :year, :title, :description, :priority)";
        Map m = new HashMap();
        m.put("userId", todo.getUserId());
        m.put("month", todo.getMonth());
        m.put("day", todo.getDay());
        m.put("year", todo.getYear());
        m.put("title", todo.getTitle());
        m.put("description", todo.getDescription());
        m.put("priority", todo.getPriority());

        SqlParameterSource ps = new MapSqlParameterSource(m);
        KeyHolder kh = new GeneratedKeyHolder();
        getNamedParameterJdbcTemplate().update(sql, ps, kh);
       /* Integer todoId = kh.getKey().intValue();
        todo.setTodoId(todoId);*/

    }

    public void update(Todo todo) {
        String sql = "UPDATE todos SET month=:month, day=:day, year=:year, title=:title, description=:description, priority=:priority  WHERE todoId=:todoId";
        Map m = new HashMap();
        m.put("todoId", todo.getTodoId());
        m.put("month", todo.getMonth());
        m.put("day", todo.getDay());
        m.put("year", todo.getYear());
        m.put("title", todo.getTitle());
        m.put("description", todo.getDescription());
        m.put("priority", todo.getPriority());

        getNamedParameterJdbcTemplate().update(sql, m);
    }

    public void delete(Todo todo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete(Long todoId) {
        String sql = "DELETE FROM todos WHERE todoId=?";
        getJdbcTemplate().update(sql, todoId);
    }

    public Todo findById(Long todoId) {
        String sql = "SELECT todoId, userId, month, day, year, title, description, priority FROM todos WHERE todoId=?";
        return getJdbcTemplate().queryForObject(sql, new TodoRowMapper(), todoId);
    }

    public List<Todo> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Todo> findByProperty(String propName, Object propValue) {
        String sql = "SELECT todoId, userId, month, day, year, title, description, priority FROM todos WHERE " + propName + "=?";
        return getJdbcTemplate().query(sql, new TodoRowMapper(), propValue);
    }

    public List<Todo> orderByPriority(String propName, Object propValue) {
        String sql = "SELECT todoId, userId, month, day, year, title, description, priority FROM todos WHERE " + propName + "=? ORDER BY priority";
        return getJdbcTemplate().query(sql, new TodoRowMapper(), propValue);
    }

}
