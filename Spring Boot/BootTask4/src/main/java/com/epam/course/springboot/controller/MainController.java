package com.epam.course.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	/*@RequestMapping("/")
	public String index() {
		return "index";
	}*/
	
	@RequestMapping("/about")
	public String about() {
		return "about";
	}
	
	@RequestMapping("/property")
	public String property() {
		return "property-grid";
	}
	
	@RequestMapping("/blog")
	public String blog() {
		return "blog-grid";
	}
	
	@RequestMapping("/contact")
	public String contact() {
		return "contact";
	}
	
	/*@RequestMapping("/property-single")
	public String property_single() {
		return "property-single";
	}*/
	
	@RequestMapping("/blog-single")
	public String blog_single() {
		return "blog-single";
	}
	
	@RequestMapping("/agents-grid")
	public String agents_grid() {
		return "agents-grid";
	}
	
	@RequestMapping("/agent-single")
	public String agent_single() {
		return "agent-single";
	}
	
	@RequestMapping(value="/404")
	public String error404() {
		return "404";
	}
}
