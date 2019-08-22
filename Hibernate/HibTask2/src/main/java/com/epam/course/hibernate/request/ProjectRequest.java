package com.epam.course.hibernate.request;

import java.util.Set;

public class ProjectRequest {
	
	private Long id;
	private String name;
	private Set<Long> empIds;
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
	public Set<Long> getEmpIds() {
		return empIds;
	}
	public void setEmpIds(Set<Long> empIds) {
		this.empIds = empIds;
	}
	
	
}
