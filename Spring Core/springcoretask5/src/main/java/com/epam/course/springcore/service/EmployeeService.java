package com.epam.course.springcore.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.course.springcore.domain.Employee;
import com.epam.course.springcore.domain.Position;
import com.epam.course.springcore.domain.Salary;
import com.epam.course.springcore.util.Util;

@Service
public class EmployeeService {
	
	@Autowired
	PositionService positionService;
	
	@Autowired
	SalaryService salaryService;
	
	
	/*
	 * 1. check the available position 
	 * 2. Assign salary
	 * 3. Assign position
	 * 4. delete from the available positions
	 */
	public Employee hire(String name,String pos) {
		
		positionService.getAvailablePositions();
		Optional<Position> position = Util.positions.stream().filter(p ->p.getName().equalsIgnoreCase(pos)).findFirst();
		Position p = null;
		Salary sal = salaryService.getSalary(pos);
		if(!position.isPresent()) {
			System.out.println("There are no jobs for the given position. Do you add this position(yes/no)");
			Scanner in = new Scanner(System.in);
			String ans = in.nextLine();
			if (ans.equalsIgnoreCase("yes")) {
				p = positionService.insertPosition(pos);
			}else {
				System.out.println("Employee cannot be added ..");
				return null;
			}
		}
		else {
			p = position.get();
		}
		if(sal == null) {
			sal = salaryService.createSalary(pos);
		}
		OptionalInt opt = Util.employees.stream().mapToInt(e -> e.getEmpid()).max();
		int maxID = 1;
		if(opt.isPresent()) {
			maxID = opt.getAsInt() + 1;
		}
		Employee e = new Employee();
		e.setEmpid(maxID);
		e.setName(name);
		e.setPos(p);
		e.setSal(sal);
		Util.employees.add(e);
		positionService.deletePosition(pos);
		showEmps();
		return e;
		
	}
	
	/*
	 * Get the position of employee and add to the 
	 * list of available positions
	 */
	public void fire(int id) {
	
		Iterator<Employee> iterator = Util.employees.iterator();
		while(iterator.hasNext()) {
			Employee emp = iterator.next();
			if(emp.getEmpid() == id) {
				iterator.remove();
				System.out.println("Employee Fired..");
			}
		}
		
		showEmps();
	}
	
	public List<Employee> showEmps() {
		Util.employees.forEach(e ->{
			System.out.println("ID :"+e.getEmpid()+"\t Name:"+e.getName()+"\t position:"+e.getPos().getName());
		});
		
		return Util.employees;
	}

}
