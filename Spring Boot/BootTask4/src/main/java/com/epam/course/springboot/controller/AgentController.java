package com.epam.course.springboot.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.course.springboot.entity.Agent;
import com.epam.course.springboot.repository.AgentRepository;

@Controller
public class AgentController {
	
	@Autowired
	AgentRepository agentRepository;
	
	@RequestMapping(value="/allAgents",method=RequestMethod.GET)
	public ModelAndView getAllProperties() {
		ModelAndView modelAndView = new ModelAndView();
		 modelAndView.addObject("agents", (ArrayList<Agent>)agentRepository.findAll());
		 modelAndView.setViewName("agents-grid");
		 return modelAndView;
	}
	
	@RequestMapping(value="/agent-single/{id}",method=RequestMethod.GET)
	public ModelAndView getProperty(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("agent", agentRepository.findById(id));
		modelAndView.setViewName("agent-single");
		return modelAndView;
	}
}
