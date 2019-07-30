package com.epam.course.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.course.springmvc.entity.User;
import com.epam.course.springmvc.model.Register;
import com.epam.course.springmvc.service.UserService;

@Controller
@RequestMapping("/")
public class LoginController {

	@Autowired
	UserService userService;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String welcomePage() {
		return "redirect:/stocks";
	}

	@RequestMapping(value = { "/homePage" }, method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/stocks");
		return model;
	}

	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public ModelAndView loginPage(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid Credentials provided.");
		}

		if (logout != null) {
			model.addObject("message", "Logged out  successfully.");
		}

		model.setViewName("login");
		return model;
	}

	/*
	 * @RequestMapping(value = "/logout", method = RequestMethod.POST) public
	 * String logout(HttpServletRequest request) {
	 * 
	 * HttpSession session = request.getSession(); session.invalidate();
	 * 
	 * return "redirect:/loginPage" ; }
	 */

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView register(
			@ModelAttribute("register") Register register) {
		ModelAndView modelAndView = new ModelAndView();
		int i = userService.addUser(register);
		String msg = null;
		if (i != 0) {
			msg = "User Registration Done .";
		} else {
			msg = "Some thing went wrong";
		}
		modelAndView.addObject("msg", msg);
		modelAndView.setViewName("register");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registerView() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("register");
		return modelAndView;
	}

}
