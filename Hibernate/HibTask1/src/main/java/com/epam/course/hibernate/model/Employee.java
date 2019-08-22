package com.epam.course.hibernate.model;

import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.epam.course.hibernate.enums.Gender;

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "emp_type",
		discriminatorType = DiscriminatorType.STRING)
public class Employee extends Person {

	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy=GenerationType.AUTO) private Long id;
	 * 
	 * @Column(unique=true)
	 * 
	 * @NotNull private String name;
	 */

	@NotNull
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@NotNull
	private LocalDate dob;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id")
	private Department department;

	@Embedded
	@AttributeOverrides(value = {
			@AttributeOverride(name = "zip", column = @Column(length = 10)),
			@AttributeOverride(name = "city",
					column = @Column(nullable = false)) })
	private Address address;

	public Employee() {
		super();
	}

	/*
	 * public Employee(@NotNull String name,
	 * 
	 * @NotNull String gender, String dept, @NotNull LocalDate dob) { super();
	 * this.name = name; this.gender = gender; this.dept = dept; this.dob = dob;
	 * }
	 */

	/*
	 * public Long getId() { return id; }
	 * 
	 * public void setId(Long id) { this.id = id; }
	 * 
	 * public String getName() { return name; }
	 * 
	 * public void setName(String name) { this.name = name; }
	 */

	public LocalDate getDob() {
		return dob;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee{" + "id=" + this.getId() + ", name='" + this.getId()
				+ '\'' + ", address=" + this.getAddress() + '}';
	}

}
