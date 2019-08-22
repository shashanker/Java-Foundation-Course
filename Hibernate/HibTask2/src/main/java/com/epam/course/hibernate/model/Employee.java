package com.epam.course.hibernate.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.epam.course.hibernate.enums.EmployeeStatus;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	@NotNull
	private String name;

	private EmployeeStatus empStatus;

	@Embedded
	@AttributeOverrides(value = {
			@AttributeOverride(name = "zip", column = @Column(length = 10)),
			@AttributeOverride(name = "city",
					column = @Column(nullable = false)) })
	private Address address;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
			mappedBy = "employee")
	private EmployeePersonalInfo personalInfo;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        joinColumns = @JoinColumn(name = "emp_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
	private Set<Project> projects;
	
	@ManyToOne
	@JoinColumn(name="unit_id",referencedColumnName="id")
	private Unit unit;
	
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

	public EmployeeStatus getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(EmployeeStatus empStatus) {
		this.empStatus = empStatus;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public EmployeePersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(EmployeePersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public boolean isExternal() {
		return external;
	}

	public void setExternal(boolean external) {
		this.external = external;
	}
	
	public void addProjects(Project project) {
		if(this.projects == null) {
			this.projects = new HashSet<>();
		}
		this.projects.add(project);
	}
	
}
