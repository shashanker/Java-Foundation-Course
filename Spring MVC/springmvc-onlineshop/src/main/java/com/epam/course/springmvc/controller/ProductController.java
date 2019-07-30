package com.epam.course.springmvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.course.springmvc.model.Product;
import com.epam.course.springmvc.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;

	/*
	 * @GetMapping("/newquote") public String beginQuoteRequest(Model model) { //
	 * add implementation later
	 * 
	 * model.addAttribute("quoteRequestForm", new QuoteRequest());
	 * 
	 * return "newQuote"; }
	 * 
	 * @PostMapping public String submitQuoteRequest() {
	 * 
	 * // add implementation later
	 * 
	 * return "newQuoteConfirmation"; }
	 */

	@GetMapping("/")
	public ModelAndView getItems(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<>();
		}
		List<Product> products = productService.getAllProducts();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("products", products);
		modelAndView.addObject("cart", cart);
		modelAndView.setViewName("products");
		return modelAndView;
	}

	@GetMapping("/showDetail/{id}")
	public ModelAndView showDetail(@PathVariable int id, HttpServletRequest req) {
		HttpSession session = req.getSession();
		Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<>();
		}
		Product product = productService.getProductById(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("product", product);
		modelAndView.addObject("cart", cart);
		modelAndView.setViewName("productDetail");
		return modelAndView;
	}

}
