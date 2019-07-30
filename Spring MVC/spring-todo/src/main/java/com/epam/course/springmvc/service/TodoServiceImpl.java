/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.course.springmvc.service;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.course.springmvc.dao.BaseDAO;
import com.epam.course.springmvc.dao.TodoDAO;
import com.epam.course.springmvc.model.Todo;
import com.epam.course.springmvc.rowmapper.TodoRowMapper;

/**
 * 
 * @author Shashanker_Vaduka
 *
 */
@Service
public class TodoServiceImpl extends BaseDAO implements TodoService {

    @Autowired
    private TodoDAO todoDao;

    public void save(Todo t) {
    	todoDao.save(t);
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update(Todo t) {
        todoDao.update(t);
    }

    public void delete(Long todoId) {
        todoDao.delete(todoId);
    }

    public Todo findById(Long todoId) {
        return todoDao.findById(todoId);
    }

    public List<Todo> findUserTodo(Long userId) {
        return todoDao.findByProperty("userId", userId);
    }

    public List<Todo> findUserTodo(Long userId, String txt) {
        String sql = "SELECT todoId, userId, month, day, year, title, description, priority FROM todo WHERE userId=? AND (month LIKE '%" + txt + "%' OR day LIKE '%" + txt + "%' OR year LIKE '%" + txt + "%' OR title LIKE '%" + txt + "%' OR description LIKE '%" + txt + "%' OR priority LIKE '%" + txt + "%')";
        return getJdbcTemplate().query(sql, new TodoRowMapper(), userId);
    }

    public List<Todo> findByPriority(Long userId) {
        return todoDao.orderByPriority("userId", userId);
    }

    public List<Todo> findUserTodoOnlyHigh(Long userId) {
        String sql = "SELECT todoId, userId, month, day, year, title, description, priority FROM todo WHERE userId=? AND priority='a'";
        return getJdbcTemplate().query(sql, new TodoRowMapper(), userId);
    }

    public List<Todo> findUserTodoOnlyMedium(Long userId) {
        String sql = "SELECT todoId, userId, month, day, year, title, description, priority FROM todo WHERE userId=? AND priority='b'";
        return getJdbcTemplate().query(sql, new TodoRowMapper(), userId);
    }

    public List<Todo> findUserTodoOnlyLow(Long userId) {
        String sql = "SELECT todoId, userId, month, day, year, title, description, priority FROM todo WHERE userId=? AND priority='c'";
        return getJdbcTemplate().query(sql, new TodoRowMapper(), userId);
    }

}
