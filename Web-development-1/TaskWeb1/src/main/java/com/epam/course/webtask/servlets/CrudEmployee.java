package com.epam.course.webtask.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.course.webtask.beans.Employee;
import com.google.gson.Gson;


@WebServlet("/employeeOperation")
public class CrudEmployee extends HttpServlet {
	
	private Gson gson = new Gson();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		String name = req.getParameter("name");
		int id = Integer.parseInt(req.getParameter("id"));
		int age = Integer.parseInt(req.getParameter("age"));
		
		//System.out.println("parameter :"+name+" "+id+" "+age);
		
		Employee emp = new Employee();
		
		emp.setId(id);
		emp.setName(name);
		emp.setAge(age);
		
		List<Employee> employees = (ArrayList<Employee>)session.getAttribute("employees");
		
		if(employees == null)
		{
			employees = new ArrayList<>();
		}
		/*for Edit action*/
		/*for(Employee e: employees)
		{
			if(e.getId() == id)
				employees.remove(e);
		}*/
		employees.add(emp);
		
		session.setAttribute("employees", employees);
		
		
		
		String employeeJsonString = this.gson.toJson(employees);
		 
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(employeeJsonString);
        out.flush();   
		
	}

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		List<Employee> employees = (ArrayList<Employee>)session.getAttribute("employees");
		PrintWriter out = resp.getWriter();
		String employeeJsonString = null;
		String action = req.getParameter("action");
		
		if(action != null && action.equals("edit"))
		{
			int id = Integer.parseInt(req.getParameter("id"));
			for(Employee emp : employees) {
				if(emp.getId() == id) {
					employeeJsonString = this.gson.toJson(emp);
				}
			}
		}
		else
			employeeJsonString = this.gson.toJson(employees);
		 
        
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(employeeJsonString);
        out.flush();  
	}
	
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));

		String data = br.readLine();
		Map<String,String>  dataMap = new HashMap<>();
		String[] splits = data.split("&");

		for (String s : splits) {
		    String[] t = s.split("=");
		    dataMap.put(t[0], t[1]);
		}
		 
		System.out.println(dataMap);
		Employee emp = new Employee();
		
		emp.setId(Integer.parseInt(dataMap.get("id")));
		emp.setName(dataMap.get("name"));
		emp.setAge(Integer.parseInt(dataMap.get("age")));
		
		List<Employee> employees = (ArrayList<Employee>)session.getAttribute("employees");
		
		if(employees == null)
		{
			employees = new ArrayList<>();
		}
		
		employees.removeIf(e -> e.getId() == emp.getId());
		
		employees.add(emp);
		
		session.setAttribute("employees", employees);
		
		
		
		String employeeJsonString = this.gson.toJson(employees);
		 
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(employeeJsonString);
        out.flush();   
		
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));

		String data = br.readLine();
		Map<String,String>  dataMap = new HashMap<>();
		String[] splits = data.split("&");

		for (String s : splits) {
		    String[] t = s.split("=");
		    dataMap.put(t[0], t[1]);
		}
		int id = Integer.parseInt(dataMap.get("id"));
		List<Employee> employees = (ArrayList<Employee>)session.getAttribute("employees");
		
		employees.removeIf(e -> e.getId() == id);
		
		session.setAttribute("employees", employees);
		
		
		String employeeJsonString = this.gson.toJson(employees);
		 
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(employeeJsonString);
        out.flush(); 
		
		
		
	}
	
	
}
