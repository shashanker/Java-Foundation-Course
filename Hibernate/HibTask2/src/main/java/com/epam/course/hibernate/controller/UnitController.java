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
import com.epam.course.hibernate.model.Unit;
import com.epam.course.hibernate.request.UnitRequest;
import com.epam.course.hibernate.response.EmployeeResponse;
import com.epam.course.hibernate.response.UnitResponse;
import com.epam.course.hibernate.service.EmployeeService;
import com.epam.course.hibernate.service.UnitService;

@RestController
@RequestMapping("/api")
public class UnitController {

	@Autowired
	private UnitService unitService;
	
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	ConversionService conversionService;

	@GetMapping(value = "/unit",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<UnitResponse>> get() {

		List<Unit> units = unitService.get();
		List<UnitResponse> unitResponses = new ArrayList<UnitResponse>();
		if(units == null) {
			return new ResponseEntity<List<UnitResponse>>(unitResponses,
					HttpStatus.NO_CONTENT);
		}
		units.stream().forEach(u -> {
			UnitResponse unitResponse = conversionService.convert(u,
					UnitResponse.class);
			unitResponses.add(unitResponse);
		});
		// .convert(reservationRequest, ReservationEntity.class););
		return new ResponseEntity<List<UnitResponse>>(unitResponses,
				HttpStatus.OK);
	}

	@PostMapping("/unit")
	public ResponseEntity<String> save(@RequestBody UnitRequest unitRequest) {
		Unit unit = conversionService.convert(unitRequest, Unit.class);
		// Department department =
		// departmentService.get(employeeRequest.getDeptId());
		// employee.setDepartment(department);
		unitService.save(unit);
		return new ResponseEntity<String>("Unit created", HttpStatus.CREATED);
	}

	@GetMapping(value = "/unit/{id}",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<UnitResponse> get(@PathVariable Long id) {
		Unit unit = unitService.get(id);

		UnitResponse unitResponse = conversionService.convert(unit,
				UnitResponse.class);
		Set<EmployeeResponse> employeeResponses = new HashSet<EmployeeResponse>();
		if(unit == null) {
			return new ResponseEntity<UnitResponse>(unitResponse, HttpStatus.NO_CONTENT);
		}
		if (unit.getEmployees() != null) {
			unit.getEmployees().stream().forEach(e -> {
				EmployeeResponse employeeResponse = conversionService.convert(e,
						EmployeeResponse.class);
				employeeResponses.add(employeeResponse);
			});
			unitResponse.setEmployees(employeeResponses);
		}

		
		return new ResponseEntity<UnitResponse>(unitResponse, HttpStatus.OK);
	}

	@DeleteMapping("/unit/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		unitService.delete(id);
		return new ResponseEntity<String>("Unit Deleted", HttpStatus.OK);
	}

	@PutMapping("/unit")
	public ResponseEntity<String> update(@RequestBody Unit unit) {
		unitService.save(unit);
		return new ResponseEntity<String>("Unit Updated", HttpStatus.ACCEPTED);
	}

	@PostMapping("/unit/{id}/employee")
	public ResponseEntity<String> addEmployeToUnit(@RequestBody UnitRequest unitRequest){
		Unit unit = conversionService.convert(unitRequest, Unit.class);
		unitRequest.getEmpIds().stream().forEach(id ->{
			Employee e = employeeService.get(id);
			
			if(e != null) {
				e.setUnit(unit);
				unit.addEmployee(e);
				
			}
			
		});
		unitService.save(unit);
		
		return new ResponseEntity<String>("Employee Added to Unit", HttpStatus.ACCEPTED);
	}
}
