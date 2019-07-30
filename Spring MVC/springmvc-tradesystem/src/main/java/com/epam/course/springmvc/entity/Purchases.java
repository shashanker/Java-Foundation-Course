package com.epam.course.springmvc.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class Purchases implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;

	@NotNull
	private Stock stock;

	@NotNull
	private Long quantity;

	@NotNull
	private Double value;

	@NotNull
	private String user;

	private String status;

	public Purchases() {
		super();
	}

	public Purchases(@NotNull Stock stock, @NotNull Long quantity,
			@NotNull Double value, @NotNull String user, String status) {
		super();
		this.stock = stock;
		this.quantity = quantity;
		this.value = value;
		this.user = user;
		this.status = status;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
