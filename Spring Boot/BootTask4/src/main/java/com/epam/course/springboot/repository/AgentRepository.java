package com.epam.course.springboot.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.epam.course.springboot.entity.Agent;

public interface AgentRepository extends CrudRepository<Agent, Serializable>{
	
	Agent findById(Long id);
}
