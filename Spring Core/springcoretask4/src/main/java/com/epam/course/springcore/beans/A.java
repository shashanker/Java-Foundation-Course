package com.epam.course.springcore.beans;

public class A {

	private String name;
	private String gender;
	private String country;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getName()+" "+this.getGender()+" "+this.getCountry();
	}
	
	
}
