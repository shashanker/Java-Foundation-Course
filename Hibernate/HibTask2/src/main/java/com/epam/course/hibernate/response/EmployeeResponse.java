package com.epam.course.hibernate.response;

import java.util.Set;

import com.epam.course.hibernate.model.Address;


public class EmployeeResponse {

	private Long id;
	private String name;
	private String status;//for employee status
	private Address address;
	private EmployeePersonalInfoResponse personalInfo;
	private Set<ProjectResponse> projects;
	private UnitResponse unit;
	private boolean external;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	
	public EmployeePersonalInfoResponse getPersonalInfo() {
		return personalInfo;
	}
	public void setPersonalInfo(EmployeePersonalInfoResponse personalInfo) {
		this.personalInfo = personalInfo;
	}
	public Set<ProjectResponse> getProjects() {
		return projects;
	}
	public void setProjects(Set<ProjectResponse> projects) {
		this.projects = projects;
	}
	public UnitResponse getUnit() {
		return unit;
	}
	public void setUnit(UnitResponse unit) {
		this.unit = unit;
	}
	public boolean isExternal() {
		return external;
	}
	public void setExternal(boolean external) {
		this.external = external;
	}
	
	
}
