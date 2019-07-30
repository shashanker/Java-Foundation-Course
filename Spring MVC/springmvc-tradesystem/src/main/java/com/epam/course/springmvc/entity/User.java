package com.epam.course.springmvc.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "User")
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String username;

	@NotNull
	private String password;

	@NotNull
	private String role;
	
	private Boolean isUserActive;

	public User() {
		super();
	}
	
	

	public User(@NotNull String username, @NotNull String password,
			@NotNull String role,Boolean isUserActive) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.isUserActive = isUserActive;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}



	public Boolean getIsUserActive() {
		return isUserActive;
	}



	public void setIsUserActive(Boolean isUserActive) {
		this.isUserActive = isUserActive;
	}

	
}
