/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.course.springmvc.service;


import java.util.List;

import com.epam.course.springmvc.model.Todo;
/**
 * 
 * @author Shashanker_Vaduka
 *
 */
public interface TodoService {

    public void save(Todo t);

    public void update(Todo t);

    public void delete(Long todoId);

    public Todo findById(Long todoId);

    public List<Todo> findUserTodo(Long userId);

    public List<Todo> findByPriority(Long userId);

    public List<Todo> findUserTodo(Long userId, String txt);

    public List<Todo> findUserTodoOnlyHigh(Long userId);

    public List<Todo> findUserTodoOnlyMedium(Long userId);

    public List<Todo> findUserTodoOnlyLow(Long userId);

}
