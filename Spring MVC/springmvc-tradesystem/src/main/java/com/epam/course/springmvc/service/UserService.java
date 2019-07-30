package com.epam.course.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.course.springmvc.entity.User;
import com.epam.course.springmvc.model.Register;
import com.epam.course.springmvc.repository.UserDAO;

@Service
public class UserService {

	
	@Autowired
	UserDAO userDAO;
	
	
	public int addUser(Register register) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUsername(register.getUsername());
		user.setPassword(register.getPassword());
		user.setRole(register.getRole());
		user.setIsUserActive(true);
		return userDAO.save(user);
	}

}
