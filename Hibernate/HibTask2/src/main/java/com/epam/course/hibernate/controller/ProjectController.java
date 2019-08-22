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
import com.epam.course.hibernate.model.Project;
import com.epam.course.hibernate.request.ProjectRequest;
import com.epam.course.hibernate.response.EmployeeResponse;
import com.epam.course.hibernate.response.ProjectResponse;
import com.epam.course.hibernate.service.EmployeeService;
import com.epam.course.hibernate.service.ProjectService;

@RestController
@RequestMapping("/api")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	ConversionService conversionService;

	@GetMapping(value = "/project",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<ProjectResponse>> get() {

		List<Project> projects = projectService.get();

		List<ProjectResponse> projectResponses = new ArrayList<ProjectResponse>();
		if(projects == null) {
			return new ResponseEntity<List<ProjectResponse>>(projectResponses,
					HttpStatus.NO_CONTENT);
		}
		projects.stream().forEach(p -> {
			ProjectResponse projectResponse = conversionService.convert(p,
					ProjectResponse.class);

			projectResponses.add(projectResponse);
		});
		// .convert(reservationRequest, ReservationEntity.class););
		return new ResponseEntity<List<ProjectResponse>>(projectResponses,
				HttpStatus.OK);
	}

	@PostMapping("/project")
	public ResponseEntity<String> save(
			@RequestBody ProjectRequest projectRequest) {
		Project project = conversionService.convert(projectRequest,
				Project.class);
		projectService.save(project);
		return new ResponseEntity<String>("Project created",
				HttpStatus.CREATED);
	}

	@GetMapping(value = "/project/{id}",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProjectResponse> get(@PathVariable Long id) {
		Project project = projectService.get(id);
		Set<EmployeeResponse> employeeResponses = new HashSet<EmployeeResponse>();
		ProjectResponse projectResponse = conversionService.convert(project,
				ProjectResponse.class);
		if(project == null) {
			return new ResponseEntity<ProjectResponse>(projectResponse,
					HttpStatus.NO_CONTENT);
		}
		if(project.getEmployees()!=null) {
			project.getEmployees().stream().forEach(e -> {
				EmployeeResponse employeeResponse = conversionService.convert(e,
						EmployeeResponse.class);
				employeeResponses.add(employeeResponse);
			});
			projectResponse.setEmployees(employeeResponses);
		}
		
		
		return new ResponseEntity<ProjectResponse>(projectResponse,
				HttpStatus.OK);
	}

	@DeleteMapping("/project/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		projectService.delete(id);
		return new ResponseEntity<String>("Project Deleted", HttpStatus.OK);
	}

	@PutMapping("/project")
	public ResponseEntity<String> update(@RequestBody Project project) {
		projectService.save(project);
		return new ResponseEntity<String>("Project Updated",
				HttpStatus.ACCEPTED);
	}

	@PostMapping("/project/{id}/employee")
	public ResponseEntity<String> addEmployeToProject(@RequestBody ProjectRequest projectRequest){
		Project project = conversionService.convert(projectRequest, Project.class);
		projectRequest.getEmpIds().stream().forEach(id ->{
			Employee e = employeeService.get(id);
			
			if(e != null) {
				e.addProjects(project);
				project.addEmployee(e);
				
			}
			
		});
		projectService.save(project);
		
		return new ResponseEntity<String>("Employee Added to Project", HttpStatus.ACCEPTED);
	}
}
