package com.epam.course.hibernate.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.epam.course.hibernate.model.Employee;
import com.epam.course.hibernate.model.EmployeePersonalInfo;
import com.epam.course.hibernate.model.Project;
import com.epam.course.hibernate.model.Unit;
import com.epam.course.hibernate.request.EmployeeRequest;
import com.epam.course.hibernate.response.EmployeeResponse;
import com.epam.course.hibernate.response.ProjectResponse;
import com.epam.course.hibernate.response.UnitResponse;
import com.epam.course.hibernate.service.EmployeeService;
import com.epam.course.hibernate.service.ProjectService;
import com.epam.course.hibernate.service.UnitService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UnitService unitService;

	@Autowired
	ConversionService conversionService;

	@GetMapping(value = "/employee",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<EmployeeResponse>> get() {

		List<Employee> employees = employeeService.get();
		List<EmployeeResponse> employeeResponses = new ArrayList<EmployeeResponse>();
		if (employees == null) {
			return new ResponseEntity<List<EmployeeResponse>>(employeeResponses,
					HttpStatus.NO_CONTENT);
		}
		
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
	public ResponseEntity<String> save(
			@RequestBody EmployeeRequest employeeRequest) {
		Employee employee = conversionService.convert(employeeRequest,
				Employee.class);
		EmployeePersonalInfo employeePersonalInfo = employee.getPersonalInfo();
		employeePersonalInfo.setEmployee(employee);
		if(employeeRequest.getProjectIds()!=null) {
			employeeRequest.getProjectIds().stream().forEach(p -> {
				Project project = projectService.get(p);
				employee.addProjects(project);
			});
		}
		if(employeeRequest.getUnitId()!=null) {
			Unit unit = unitService.get(employeeRequest.getUnitId());
			employee.setUnit(unit);
		}
		
		employeeService.save(employee);
		return new ResponseEntity<String>("Employee created",
				HttpStatus.CREATED);
	}

	@GetMapping(value = "/employee/{id}",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<EmployeeResponse> get(@PathVariable Long id) {
		Employee employee = employeeService.get(id);
		
		EmployeeResponse employeeResponse = conversionService
				.convert(employeeService.get(id), EmployeeResponse.class);
		if(employee == null) {
			return new ResponseEntity<EmployeeResponse>(employeeResponse,
					HttpStatus.NO_CONTENT);
		}
		Set<ProjectResponse> projectResponses = new HashSet<>();
		if(employee.getProjects()!=null) {
			employee.getProjects().stream().forEach(p -> {
				ProjectResponse projectResponse = new ProjectResponse();
				projectResponse.setId(p.getId());
				projectResponse.setName(p.getName());
				projectResponses.add(projectResponse);
			});
		}
		if(employee.getUnit()!=null) {
			UnitResponse unitResponse = new UnitResponse();
			unitResponse.setId(employee.getUnit().getId());
			unitResponse.setName(employee.getUnit().getName());
			employeeResponse.setUnit(unitResponse);
		}
		employeeResponse.setProjects(projectResponses);
		
		return new ResponseEntity<EmployeeResponse>(employeeResponse,
				HttpStatus.OK);
	}

	@DeleteMapping("/employee/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		employeeService.delete(id);
		return new ResponseEntity<String>("Employee Deleted", HttpStatus.OK);
	}

	
	@PutMapping("/employee")
	public ResponseEntity<String> update(@RequestBody EmployeeRequest employeeRequest) {
		Employee employee = conversionService.convert(employeeRequest,
				Employee.class);
		employeeService.save(employee);
		
		return new ResponseEntity<String>("Employee Updated",
				HttpStatus.ACCEPTED);
	}

}
