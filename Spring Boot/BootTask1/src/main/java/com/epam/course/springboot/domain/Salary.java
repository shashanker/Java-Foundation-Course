package com.epam.course.springboot.domain;

public class Salary {

	private int sid;
	private Position pos;
	private double totSal;
	private double baseSal;
	private double inflation;

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

	public double getTotSal() {
		totSal = this.baseSal + (this.inflation * this.baseSal) / 100;
		return totSal;
	}

	public double getBaseSal() {
		return baseSal;
	}

	public void setBaseSal(double baseSal) {
		this.baseSal = baseSal;
	}

	public double getInflation() {
		return inflation;
	}

	public void setInflation(double inflation) {
		this.inflation = inflation;
	}
}
