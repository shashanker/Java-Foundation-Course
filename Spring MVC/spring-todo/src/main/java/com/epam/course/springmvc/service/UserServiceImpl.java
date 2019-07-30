/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.course.springmvc.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.epam.course.springmvc.dao.BaseDAO;
import com.epam.course.springmvc.dao.TodoDAO;
import com.epam.course.springmvc.dao.UserDAO;
import com.epam.course.springmvc.domain.Login;
import com.epam.course.springmvc.model.Todo;
import com.epam.course.springmvc.model.User;
import com.epam.course.springmvc.rowmapper.UserRowMapper;

/**
 * 
 * @author Shashanker_Vaduka
 *
 */
@Service
public class UserServiceImpl extends BaseDAO implements UserService {

    @Autowired
    private UserDAO userDao;
    
    @Autowired
    private TodoDAO todoDao;

    public void register(User u) {
        userDao.save(u);
    }

    public User login(User u) {
        return userDao.find(u);
        

    }

    public void saveTodo(Todo t) {
       todoDao.save(t);
    }


}
