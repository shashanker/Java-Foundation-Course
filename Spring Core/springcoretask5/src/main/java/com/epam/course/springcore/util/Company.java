package com.epam.course.springcore.util;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.course.springcore.domain.Employee;
import com.epam.course.springcore.service.EmployeeService;
import com.epam.course.springcore.service.PositionService;
import com.epam.course.springcore.service.SalaryService;

public class Company {
	
	@Autowired
	PositionService positionService;
	
	@Autowired()
	EmployeeService employeeService;

	@Autowired
	SalaryService salaryService;

	public void addPosition() {
		// TODO Auto-generated method stub
		
	}
	
	public void showOptions() {
		System.out.println("-----choose one option  -----");
		System.out.println("1 . List open Position");
		System.out.println("2 . Insert a new Position");
		System.out.println("3 . Delete a Position");
		System.out.println("4 . Update a Position");
		System.out.println("5 . Update Salary For a Position");
		System.out.println("6 . Hire Employee");
		System.out.println("7 . Fire Employee");
		System.out.println("9 . Exit");
		Scanner in = new Scanner(System.in);
		int opt = in.nextInt();
		
		switch (opt) {
		case 1:
			positionService.getAvailablePositions();
			break;
		case 2:
			System.out.println("Enter Position name to Insert");
			String eValue = in.nextLine();
			while(eValue.equals("")) {
				eValue = in.nextLine();
			}
			
			positionService.insertPosition(eValue);
			break;
		case 3:
			System.out.println("Enter Position Name to delete");
			String dValue = in.nextLine();
			positionService.getAvailablePositions();
			positionService.deletePosition(dValue);
			break;
		case 4:
			System.out.println("Enter Name of Position to Update");
			String iValue = in.nextLine();
			System.out.println();
			System.out.println("Enter Updated Name for the Position");
			String uValue = in.nextLine();
			positionService.getAvailablePositions();
			positionService.updatePosition(iValue,uValue);
			break;
		case 5:
			System.out.println("Enter Name of Position for Salary Update");
			String pos = in.nextLine();
			while(pos.equals("")) {
				pos = in.nextLine();
			}
			System.out.println("Enter updated Base Salary for the position "+pos);
			Double sal = in.nextDouble();
			salaryService.updateSalary(pos,sal);
			break;
		case 6 :
			System.out.print("Enter Name of Employee");
			String name = in.nextLine();
			while(name.equals("")) {
				name = in.nextLine();
			}
			System.out.print("Enter the Position");
			pos = in.nextLine();
			while(pos.equals("")) {
				pos = in.nextLine();
			}
			employeeService.hire(name, pos);
			break;
		case 7 :
			List<Employee> employees = employeeService.showEmps();
			if(employees.size()==0) {
					System.out.println("There are no employees ...");
					break;
			}
			System.out.println("select empId from the list");
			int id = in.nextInt();
			employeeService.fire(id);
			break;
		case 9:
			System.out.println("Thanks Visit Again ....");
			break;
		default:
			break;
		}
		
		if(opt!=9) {
			showOptions();
		}
	}
	

}
