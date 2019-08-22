package com.epam.course.hibernate.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("contractor")
public class ContractEmployee extends Employee {
	
	private Double hourlyPay;
	private Long hoursWorked;
	public Double getHourlyPay() {
		return hourlyPay;
	}
	public void setHourlyPay(Double hourlyPay) {
		this.hourlyPay = hourlyPay;
	}
	public Long getHoursWorked() {
		return hoursWorked;
	}
	public void setHoursWorked(Long hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
	

}
