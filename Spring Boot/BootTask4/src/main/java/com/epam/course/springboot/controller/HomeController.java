package com.epam.course.springboot.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.course.springboot.entity.Agent;
import com.epam.course.springboot.entity.Property;
import com.epam.course.springboot.enums.Status;
import com.epam.course.springboot.repository.AgentRepository;
import com.epam.course.springboot.repository.PropertyRepository;

@Controller
public class HomeController
{
	
	@Autowired
	PropertyRepository propertyRepository;
	
	@Autowired
	AgentRepository agentRepository;
	
	 @RequestMapping(value="/",method=RequestMethod.GET)
	    public ModelAndView home()
	    {
		 	/*request.getUserPrincipal().getName();
	        return "Welecome "+request.getUserPrincipal().getName()+" !!!";*/
		
		 ModelAndView modelAndView = new ModelAndView();
		// propertyRepository.findAvaliableProperty(Status.OPEN);
		 modelAndView.addObject("properties", (ArrayList<Property>)propertyRepository.findAvaliableProperty(Status.OPEN));
		 modelAndView.addObject("agents", (ArrayList<Agent>)agentRepository.findAll());
		 modelAndView.setViewName("index");
		 return modelAndView;

	    }
	 
	 
	
    /*@RequestMapping("/user")
    public String showUserMsg()
    {
        return "User has logged in!!!";

    }

    @RequestMapping("/admin")
    public String showAdminMsg()
    {
        return "Admin has logged in!!!";
    }*/
}