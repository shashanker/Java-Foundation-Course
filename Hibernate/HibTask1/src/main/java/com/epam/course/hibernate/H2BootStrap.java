package com.epam.course.hibernate;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.epam.course.hibernate.dao.DepartmentDAO;
import com.epam.course.hibernate.dao.EmployeeDAO;
import com.epam.course.hibernate.enums.Gender;
import com.epam.course.hibernate.model.Address;
import com.epam.course.hibernate.model.Department;
import com.epam.course.hibernate.model.Employee;
import com.epam.course.hibernate.model.FullTimeEmployee;


/**
 * 
 * @author Shashanker_Vaduka
 *
 *h2 boot strap. This class will pre-populate some data in the room table so that we have data to work with. 
 */

@Component
public class H2BootStrap implements CommandLineRunner{

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	EmployeeDAO employeeDAO;
	
	@Autowired
	DepartmentDAO departmentDAO;
	
	@Transactional
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("Bootstrapping data : ");
		
		Department department = new Department();
		department.setDepartmentName("JAVA");
		
		Employee employee = new FullTimeEmployee();
		employee.setName("shashanker");
		employee.setGender(Gender.MALE);
		employee.setDepartment(department);
		employee.setDob(LocalDate.of(1999, 9, 12));
		
		Address address = new Address("Lingampally", "Hyderabad", "500019", "Telangana");
		employee.setAddress(address);
		employeeDAO.save(employee);
		
		//employeeDAO.get();
		departmentDAO.get();
		System.out.println("Bootstrapping data done: ");
	}

}
