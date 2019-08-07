package com.epam.course.springcore.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.course.springboot.domain.Employee;
import com.epam.course.springboot.domain.Position;
import com.epam.course.springboot.domain.Salary;



public class Util {
	
	
	private static final Logger logger = LoggerFactory.getLogger(Util.class);
	public static List<Employee> employees = null;
	
	public static List<Position> positions = null;
	
	public static List<Salary> salaries = null;
	
	public static Map<String,Integer> positionMap = null;
	
	
	public static void initApp(){
		logger.debug("Bootstrapping Data Started.");
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
		position3.setPid(3);
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
		salary2.setSid(2);
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
		logger.debug("Bootstrapping Data Completed.");
	}

}
