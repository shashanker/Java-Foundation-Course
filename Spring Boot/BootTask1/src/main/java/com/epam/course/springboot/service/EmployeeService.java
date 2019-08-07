package com.epam.course.springboot.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.course.springboot.domain.Employee;
import com.epam.course.springboot.domain.Position;
import com.epam.course.springboot.domain.Salary;
import com.epam.course.springcore.util.Util;

@Service("EmployeeService")
public class EmployeeService implements CompanyService{
	
	
	private final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	
	@Autowired
	PositionService positionService;
	
	@Autowired
	SalaryService salaryService;
	
	
	
	
	public EmployeeService() {
		super();
	}

	public EmployeeService(String msg) {
		super();
		logger.debug("Employee Service with "+msg +" profile");
		
	}

	/*
	 * 1. check the available position 
	 * 2. Assign salary
	 * 3. Assign position
	 * 4. delete from the available positions
	 */
	@SuppressWarnings("finally")
	public Employee hire(String name,String pos) {
		logger.debug("Hire method called!");
		
		try {
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
			/*OptionalInt opt = Util.employees.stream().mapToInt(e -> e.getEmpid()).max();
			int maxID = 1;
			if(opt.isPresent()) {
				maxID = opt.getAsInt() + 1;
			}*/
			Employee e = new Employee();
			//e.setEmpid(maxID);
			e.setName(name);
			e.setPos(p);
			e.setSal(sal);
			Util.employees.add(e);
			positionService.deletePosition(pos);
			showEmps();
			return e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error in Hire() :"+ e.getMessage());
		}
		finally {
			return null;
		}
	}
	
	/*
	 * Get the position of employee and add to the 
	 * list of available positions
	 */
	public void fire(int id) {
		logger.debug("Fire method called!");
		try {
			Iterator<Employee> iterator = Util.employees.iterator();
			while(iterator.hasNext()) {
				Employee emp = iterator.next();
				if(emp.getEmpid() == id) {
					iterator.remove();
					System.out.println("Employee Fired..");
				}
			}
			
			showEmps();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error in Fire() :"+ e.getMessage());
		}
	}
	
	@SuppressWarnings("finally")
	public List<Employee> showEmps() {
		
		logger.debug("showEmps() called!");
		try {
			Util.employees.forEach(e ->{
				System.out.println("ID :"+e.getEmpid()+"\t Name:"+e.getName()+"\t position:"+e.getPos().getName());
			});
			
			return Util.employees;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error in showEmps() :"+ e.getMessage());
		}
		finally {
			return null;
		}
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println("Servicing Request by EmployeeService....");
	}

	

}
