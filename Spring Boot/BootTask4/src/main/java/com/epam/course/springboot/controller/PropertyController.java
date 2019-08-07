package com.epam.course.springboot.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.course.springboot.entity.Property;
import com.epam.course.springboot.enums.Status;
import com.epam.course.springboot.repository.PropertyRepository;

@Controller
public class PropertyController {
	
	@Autowired
	PropertyRepository propertyRepository;
	
	@RequestMapping(value="/allprops",method=RequestMethod.GET)
	public ModelAndView getAllProperties() {
		ModelAndView modelAndView = new ModelAndView();
		 modelAndView.addObject("properties", (ArrayList<Property>)propertyRepository.findAvaliableProperty(Status.OPEN));
		 modelAndView.setViewName("property-grid");
		 return modelAndView;
	}
	
	
	@RequestMapping(value="/property-single/{id}",method=RequestMethod.GET)
	public ModelAndView getProperty(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("property", propertyRepository.findById(id));
		modelAndView.setViewName("property-single");
		return modelAndView;
	}
	
	@RequestMapping(value="/buy",method=RequestMethod.POST)
	public ModelAndView buyProperty(@RequestParam(name="propertyId") Long id)
	{
		ModelAndView modelAndView = new ModelAndView();
		Property property = propertyRepository.findById(id);
		property.setStatus(Status.CLOSED);
		propertyRepository.save(property);
		propertyRepository.findAll();
	//	modelAndView.addObject("property", propertyRepository.findById(id));
		modelAndView.setViewName("thankCustomer");
		return modelAndView;
	}
}
