package com.epam.course.springmvc.model;

import javax.validation.constraints.NotNull;

public class Register {
	@NotNull
	private String username;
	@NotNull
	private String password;
	@NotNull
	private String role;
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
	
	

}
