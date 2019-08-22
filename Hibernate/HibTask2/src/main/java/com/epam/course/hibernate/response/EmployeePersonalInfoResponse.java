package com.epam.course.hibernate.response;


public class EmployeePersonalInfoResponse {

	private String personalEmail;
	private String mobile;
	private String permenantAdress;
	private String currentAddress;
	public String getPersonalEmail() {
		return personalEmail;
	}
	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPermenantAdress() {
		return permenantAdress;
	}
	public void setPermenantAdress(String permenantAdress) {
		this.permenantAdress = permenantAdress;
	}
	public String getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	
	
}
