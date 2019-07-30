package com.epam.course.springmvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.course.springmvc.entity.Stock;
import com.epam.course.springmvc.service.StockService;

@Controller
@RequestMapping("/")
public class StockController {

	@Autowired
	StockService stockService;

	@RequestMapping(value = { "/stocks" }, method = RequestMethod.GET)
	public ModelAndView getStocks(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Integer cart = (Integer) session.getAttribute("cart");
		if (cart == null) {
			cart = 0;
		}
		List<Stock> stocks = stockService.getAllStocks();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("stocks", stocks);
		modelAndView.addObject("cart", cart);
		modelAndView.setViewName("stocks");
		return modelAndView;
	}
}
