package com.epam.course.hibernate.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable{

	private String street;
	private String city;
	private String zip;
	private String state;

	public Address() {
	}

	public Address(String street, String city, String zip, String state) {
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.state = state;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Address{" + "street='" + street + '\'' + ", city='" + city
				+ '\'' + ", zip='" + zip + '\'' + ", state='" + state + '\''
				+ '}';
	}
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof Address)) return false;
	        Address address = (Address) o;
	        return Objects.equals(getStreet(), address.getStreet()) &&
	                Objects.equals(getCity(), address.getCity()) && 
	                Objects.equals(getStreet(), address.getStreet()) &&
	                Objects.equals(getZip(), address.getZip());
	    }
	 
	    @Override
	    public int hashCode() {
	        return Objects.hash(getZip());
	    }
}