package com.task.model;

import java.util.Map;

public class UserAccount {

	private int id;
	private String name;

	private Map<Currency, Double> deposits;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * public double getBalance() { return balance; } public void setBalance(double
	 * balance) { this.balance = balance; } public String getCurrency() { return
	 * currency; } public void setCurrency(String currency) { this.currency =
	 * currency; }
	 */
	public Map<Currency, Double> getDeposits() {
		return deposits;
	}

	public void setDeposits(Map<Currency, Double> deposits) {
		this.deposits = deposits;
	}

}
