package com.epam.course.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.course.springmvc.domain.Login;
import com.epam.course.springmvc.domain.Register;
import com.epam.course.springmvc.model.User;
import com.epam.course.springmvc.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String todo() {
		return "home";
	}
	

	@RequestMapping(value = { "/reg_form" })
	public String register(Model m) {
		Register register = new Register();
		m.addAttribute("register", register);
		return "register";
	}
	
	@RequestMapping(value = { "/register" },method=RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("register") Register register) {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		user.setUsername(register.getUsername());
		user.setPassword(register.getPassword());
		userService.register(user);
		modelAndView.addObject("msg", "User Registered Successfully");
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	
	@RequestMapping(value = {"/loginform"})
    public String loginForm(Model m) {
		Login login = new Login();
		m.addAttribute("login", login);
        return "login";
    }
	
	 @RequestMapping(value = {"/login"})
	    public ModelAndView logindo(@ModelAttribute("login") Login login, ModelAndView modelAndView, HttpServletRequest request) {
		 	User user = new User(login.getUsername(), login.getPassword());
	        User loggedinUser = userService.login(user);
	        if (loggedinUser == null) {
	        	
	        	modelAndView.addObject("error", "You provided wrong information.</br>Please provide valid credentials");
	             modelAndView.setViewName("login");
	        } else {
	            addUserInSession(loggedinUser, request);
	           User u = (User)request.getSession().getAttribute("user");
	           Long id = (Long)request.getSession().getAttribute("userId");
	             modelAndView.setViewName("redirect:addTask?act=taskpage");
	        }
	        return modelAndView;
	    }
	 
	 @RequestMapping(value ="/logout")
	    public String logout(HttpSession session){
	    session.invalidate();
	    return "redirect:/";
	    }
	 
	 private void addUserInSession(User u,HttpServletRequest request) {
		 	HttpSession session = request.getSession();
	        session.setAttribute("user", u);
	        session.setAttribute("userId", u.getId());
	    }
}
