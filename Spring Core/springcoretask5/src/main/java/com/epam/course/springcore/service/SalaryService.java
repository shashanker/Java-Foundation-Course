package com.epam.course.springcore.service;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.epam.course.springcore.domain.Position;
import com.epam.course.springcore.domain.Salary;
import com.epam.course.springcore.util.Util;


@Service
public class SalaryService {
	
	
	/*
	 * update Salaries of all employees for given position	
	 * 
	 */
	public void updateSalary(String pos,double sal) {
		
		Optional<Salary> salaryOpt = Util.salaries.stream().filter(p -> p.getPos().getName().equalsIgnoreCase(pos)).findAny();
		if(salaryOpt.isPresent()) {
			
			Salary salary = salaryOpt.get();
			salary.setBaseSal(sal);
			
			Util.salaries.forEach(s -> {
				if(s.getPos().getName().equalsIgnoreCase("pos")) {
					s = salary;
				}
			});
			
			System.out.println("List of Salaries");
			
		}
		else {
			System.out.println("No position is available to name :"+pos);
		}
		for (Salary salary : Util.salaries) {
			System.out.println("Position : "+salary.getPos().getName()+"\t Salary : "+salary.getTotSal());
		}
	}

	public Salary getSalary(String pos) {
		Optional<Salary> salaryOpt = Util.salaries.stream().filter(p -> p.getPos().getName().equalsIgnoreCase(pos)).findAny();
		Salary salary = null;
		if(salaryOpt.isPresent()) {
			salary = salaryOpt.get();
		}
		return salary;
	}
	
	public Salary createSalary(String pos) {
		
		OptionalInt max = Util.salaries.stream().mapToInt(s ->s.getSid()).max();
		int maxID = 1;
		if(max.isPresent()) {
			maxID = max.getAsInt() + 1;
		}
		Salary salary = new Salary();
		System.out.println("Enter the base Salary for position :"+pos);
		Scanner in = new Scanner(System.in);
		Double baseSal = in.nextDouble();
		salary.setSid(maxID);
		salary.setBaseSal(baseSal);
		salary.setInflation(5);
		Random random = new Random();
		Position p = new Position();
		p.setPid(random.nextInt(1000));
		p.setName(pos);
		salary.setPos(p);
		Util.salaries.add(salary);
		return salary;
	}
}
