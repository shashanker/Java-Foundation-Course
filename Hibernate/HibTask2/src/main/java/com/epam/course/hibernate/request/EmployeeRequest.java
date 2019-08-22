package com.epam.course.hibernate.request;

import java.util.Set;

import com.epam.course.hibernate.model.Address;
import com.epam.course.hibernate.model.EmployeePersonalInfo;

public class EmployeeRequest {

	private Long id;
	private String name;
	private String status;//for employee status
	private Address address;
	private EmployeePersonalInfo employeePersonalInfo;
	private Set<Long> projectIds;//for projects
	private Long unitId;
	private boolean external;
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
	public EmployeePersonalInfo getEmployeePersonalInfo() {
		return employeePersonalInfo;
	}
	public void setEmployeePersonalInfo(EmployeePersonalInfo employeePersonalInfo) {
		this.employeePersonalInfo = employeePersonalInfo;
	}
	public Set<Long> getProjectIds() {
		return projectIds;
	}
	public void setProjectIds(Set<Long> projectIds) {
		this.projectIds = projectIds;
	}
	public Long getUnitId() {
		return unitId;
	}
	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}
	public boolean isExternal() {
		return external;
	}
	public void setExternal(boolean external) {
		this.external = external;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
