package com.epam.course.springboot.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.epam.course.springboot.entity.User;

public interface UserRepository extends CrudRepository<User, Serializable>{
	
	User findById(Long id);
	User findByUsername(String username);
}
