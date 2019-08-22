package com.epam.course.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.course.hibernate.dao.EmployeeDAO;
import com.epam.course.hibernate.model.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;

	@Transactional
	public List<Employee> get() {
		return employeeDAO.get();
	}

	@Transactional
	public Employee get(Long id) {
		return employeeDAO.get(id);
	}

	@Transactional
	public void save(Employee employee) {
		employeeDAO.save(employee);
	}

	@Transactional
	public void delete(Long id) {
		employeeDAO.delete(id);
	}
	
	@Transactional
	public List<Employee> findByGender(String gender){
		return employeeDAO.findByGender(gender);
	}

}
