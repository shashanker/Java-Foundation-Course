package com.epam.course.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.course.hibernate.dao.ProjectDAO;
import com.epam.course.hibernate.model.Project;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectDAO projectDAO;
	
	@Transactional
	public List<Project> get() {
		return projectDAO.get();
	}

	@Transactional
	public Project get(Long id) {
		return projectDAO.get(id);
	}

	@Transactional
	public void save(Project project) {
		projectDAO.save(project);
	}

	@Transactional
	public void delete(Long id) {
		projectDAO.delete(id);
	}
	

}
