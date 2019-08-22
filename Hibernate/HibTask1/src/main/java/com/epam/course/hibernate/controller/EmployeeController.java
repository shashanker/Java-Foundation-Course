package com.epam.course.hibernate.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.course.hibernate.model.Department;
import com.epam.course.hibernate.model.Employee;
import com.epam.course.hibernate.request.EmployeeRequest;
import com.epam.course.hibernate.response.EmployeeResponse;
import com.epam.course.hibernate.service.DepartmentService;
import com.epam.course.hibernate.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DepartmentService departmentService;

	@Autowired
	ConversionService conversionService;

	@GetMapping(value = "/employee",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<EmployeeResponse>> get() {

		List<Employee> employees = employeeService.get();
		List<EmployeeResponse> employeeResponses = new ArrayList<EmployeeResponse>();
		employees.stream().forEach(e -> {
			EmployeeResponse employeeResponse = conversionService.convert(e,
					EmployeeResponse.class);
			employeeResponses.add(employeeResponse);
		});
		// .convert(reservationRequest, ReservationEntity.class););
		return new ResponseEntity<List<EmployeeResponse>>(employeeResponses,
				HttpStatus.OK);
	}

	@PostMapping("/employee")
	public ResponseEntity<String> save(@RequestBody EmployeeRequest employeeRequest) {
		Employee employee = conversionService.convert(employeeRequest, Employee.class);
		Department department = departmentService.get(employeeRequest.getDeptId());
		employee.setDepartment(department);
		employeeService.save(employee);
		return new ResponseEntity<String>("Employee created",
				HttpStatus.CREATED);
	}

	@GetMapping(value = "/employee/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<EmployeeResponse> get(@PathVariable Long id) {
		EmployeeResponse employeeResponse =conversionService.convert(employeeService.get(id), EmployeeResponse.class);
		return new ResponseEntity<EmployeeResponse>(employeeResponse,HttpStatus.OK);
	}

	@DeleteMapping("/employee/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		employeeService.delete(id);
		return new ResponseEntity<String>("Employee Deleted", HttpStatus.OK);
	}

	@PutMapping("/employee")
	public ResponseEntity<String> update(@RequestBody Employee employee) {
		employeeService.save(employee);
		return new ResponseEntity<String>("Employee Updated",
				HttpStatus.ACCEPTED);
	}

}
