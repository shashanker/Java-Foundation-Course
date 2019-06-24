package com.epam.course.springcore.beans;

public class B {

	A a;

	B(A a) {
		this.a = a;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.a.toString();
	}
}
