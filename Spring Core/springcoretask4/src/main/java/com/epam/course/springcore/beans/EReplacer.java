package com.epam.course.springcore.beans;

import java.lang.reflect.Method;

import org.springframework.beans.factory.support.MethodReplacer;

public class EReplacer implements MethodReplacer{

	public Object reimplement(Object obj, Method meth, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		if(meth.getName().equals("print")) {
			System.out.println("E  output from replaced method source");
		}
		
		return null;
	}
}
