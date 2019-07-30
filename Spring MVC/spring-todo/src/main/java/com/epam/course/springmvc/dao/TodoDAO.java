/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.course.springmvc.dao;

import java.util.List;

import com.epam.course.springmvc.model.Todo;

/**
 * 
 * @author Shashanker_Vaduka
 *
 */
public interface TodoDAO {

    public void save(Todo todo);

    public void update(Todo todo);

    public void delete(Todo todo);

    public void delete(Long todoId);

    public Todo findById(Long todoId);

    public List<Todo> findAll();

    public List<Todo> findByProperty(String propName, Object propValue);

    public List<Todo> orderByPriority(String propName, Object propValue);

}
