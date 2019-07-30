package com.epam.course.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.course.springmvc.model.Todo;
import com.epam.course.springmvc.service.TodoService;
import com.epam.course.springmvc.service.UserService;

@Controller
public class TodoController {

	@Autowired
	private UserService userService;

	@Autowired
	private TodoService todoservice;

	@RequestMapping(value = { "/addTask" })
	public String addTask(Model m) {
		Todo todo = new Todo();
		m.addAttribute("todo", todo);

		return "addNewTask";
	}

	@RequestMapping(value = { "/saveAddedTask" })
	public String saveOrUpdateAddedTask(@ModelAttribute("todo") Todo todo,
			Model m, HttpSession session) {
		Long todoId = todo.getTodoId();
		if (todoId == null) {
			Long userId = (Long) session.getAttribute("userId");
			todo.setUserId(userId);
			// userService.saveTodo(todo);

			todoservice.save(todo);

			return "redirect:todolist?act=worklist";
		} else {
			todo.setTodoId(todoId);
			todoservice.update(todo);
			return "redirect:todolist?act=worklist";

		}

	}
	
	
	 @RequestMapping(value = {"/todolist"})
	    public ModelAndView goToTodolist(Model m, HttpSession session) {

	        Long userId = (Long) session.getAttribute("userId");
	       // m.addAttribute("todolists", todoservice.findUserTodo(userId));
	        ModelAndView modelAndView = new ModelAndView();
	        List<Todo> todos  =todoservice.findUserTodo(userId);
	        modelAndView.addObject("todolists", todos);	
	        modelAndView.setViewName("todolist");
	        return modelAndView;
	    }
	 
	 @RequestMapping(value = {"/edit_todolist"})
	    public String editList(Model m, HttpSession session, @RequestParam("todoId") Long todoId) {

	        session.setAttribute("pTodoId", todoId);
	        Todo t = todoservice.findById(todoId);
	        m.addAttribute("todo", t);
	        return "addNewTask";
	    }
	 
	 @RequestMapping(value = "/del_todolist")
	    public String deleteTodoList(@RequestParam("todoId") Long todoId) {

	        todoservice.delete(todoId);
	        return "redirect:todolist?act=del";
	    }

}
