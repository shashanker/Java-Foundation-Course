package com.epam.course.springcore.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.course.springcore.domain.Employee;
import com.epam.course.springcore.domain.Position;
import com.epam.course.springcore.domain.Salary;

public class Util {
	
	public static List<Employee> employees = null;
	
	public static List<Position> positions = null;
	
	public static List<Salary> salaries = null;
	
	public static Map<String,Integer> positionMap = null;
	
	
	public static void initApp(){
		positions = new ArrayList<>();
		Position position1 = new Position();
		position1.setPid(1);
		position1.setName("Software Trainee");
		positions.add(position1);
		
		Position position2 = new Position();
		position2.setPid(2);
		position2.setName("Software Engineer");
		positions.add(position2);
		
		
		Position position3 = new Position();
		position3.setPid(2);
		position3.setName("Software Trainee");
		positions.add(position3);
		
		salaries = new ArrayList<>();
		Salary salary1 = new Salary();
		salary1.setSid(1);
		salary1.setPos(position1);
		salary1.setBaseSal(200000);
		salary1.setInflation(5);
		salaries.add(salary1);
		
		Salary salary2 = new Salary();
		salary2.setSid(1);
		salary2.setPos(position2);
		salary2.setBaseSal(300000);
		salary2.setInflation(5);
		salaries.add(salary2);
		
		positionMap = new HashMap<>();
		positions.forEach(p -> {
			int count = positionMap.get(p.getName())==null?0:positionMap.get(p.getName());
			positionMap.put(p.getName(), ++count);
		});
		
		employees = new ArrayList<>();
	
	}

}
