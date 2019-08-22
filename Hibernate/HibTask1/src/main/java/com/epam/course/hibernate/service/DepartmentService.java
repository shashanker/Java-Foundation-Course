package com.epam.course.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.course.hibernate.dao.DepartmentDAO;
import com.epam.course.hibernate.model.Department;

@Service
public class DepartmentService {

	@Autowired
	DepartmentDAO departmentDAO;

	public List<Department> get() {
		return departmentDAO.get();
	}

	public Department get(Long id) {
		return departmentDAO.get(id);
	}

	public void save(Department department) {
		departmentDAO.save(department);
	}

	public void delete(Long id) {
		departmentDAO.delete(id);
	}

	public Department findByName(String name) {
		return departmentDAO.findByName(name);
	}

}
