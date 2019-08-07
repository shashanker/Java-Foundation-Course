package com.epam.course.springboot.service;

import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.epam.course.springboot.domain.Position;
import com.epam.course.springboot.domain.Salary;
import com.epam.course.springcore.util.Util;


@Service("SalaryService")
public class SalaryService implements CompanyService{
	
	private final Logger logger = LoggerFactory.getLogger(SalaryService.class);
	
	/*
	 * update Salaries of all employees for given position	
	 * 
	 */
	public void updateSalary(String pos,double sal) {
		logger.debug("updateSalary method called!");
		try {
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error in updateSalary() :"+ e.getMessage());
		}
	}

	@SuppressWarnings("finally")
	public Salary getSalary(String pos) {
		logger.debug("getSalary method called!");
		try {
			Optional<Salary> salaryOpt = Util.salaries.stream().filter(p -> p.getPos().getName().equalsIgnoreCase(pos)).findAny();
			Salary salary = null;
			if(salaryOpt.isPresent()) {
				salary = salaryOpt.get();
			}
			return salary;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error in getSalary() :"+ e.getMessage());
		}
		finally {
			return null;
		}
	}
	
	@SuppressWarnings("finally")
	public Salary createSalary(String pos) {
		logger.debug("createSalary method called!");
		/*OptionalInt max = Util.salaries.stream().mapToInt(s ->s.getSid()).max();
		int maxID = 1;
		if(max.isPresent()) {
			maxID = max.getAsInt() + 1;
		}*/
		try {
			Salary salary = new Salary();
			System.out.println("Enter the base Salary for position :"+pos);
			Scanner in = new Scanner(System.in);
			Double baseSal = in.nextDouble();
			//salary.setSid(maxID);
			salary.setBaseSal(baseSal);
			salary.setInflation(5);
			Random random = new Random();
			Position p = new Position();
			//p.setPid(random.nextInt(1000));
			p.setName(pos);
			salary.setPos(p);
			Util.salaries.add(salary);
			return salary;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error in createSalary() :"+ e.getMessage());
		}
		finally {
			return null;
		}
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println("Servicing Request by SalaryService....");
	}
}
