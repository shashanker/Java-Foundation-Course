package com.epam.course.hibernate.response;

import java.util.Set;

public class UnitResponse {
	
	private Long id;
	private String name;
	private Set<EmployeeResponse> employees;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<EmployeeResponse> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<EmployeeResponse> employees) {
		this.employees = employees;
	}

}
