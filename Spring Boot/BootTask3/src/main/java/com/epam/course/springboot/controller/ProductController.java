package com.epam.course.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.course.springboot.entity.Product;
import com.epam.course.springboot.exception.ProductNotfoundException;
import com.epam.course.springboot.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
		Flux<Product> productFlux = productService.getAllProducts();
		List<Product> products = productFlux.toStream().collect(Collectors.toList());
		if(products == null || products.size()==0) {
			throw new ProductNotfoundException();
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("products", products);
		modelAndView.addObject("cart", cart);
		modelAndView.setViewName("products");
		
		return modelAndView;
	}

	@GetMapping("/showDetail/{id}")
	public ModelAndView showDetail(@PathVariable Long id, HttpServletRequest req) {
		HttpSession session = req.getSession();
		Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<>();
		}
		Mono<Product> productMono = productService.getProductById(id);
		Product product = productMono.flux().toStream().findAny().get();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("product", product);
		modelAndView.addObject("cart", cart);
		modelAndView.setViewName("productDetail");
		return modelAndView;
	}
	
	@RequestMapping(value="/404")
	public String error404() {
		return "404";
	}
	
	@RequestMapping("/api/products")
	public Flux<Product> getAllProducts(){
		return productService.getAllProducts();
	}

	@GetMapping("/api/product/{id}")
	public Mono<Product> getAllProducts(@PathVariable Long id){
		
		/*Mono<Product> productMono = productService.getProductById(id);
		return productMono
        .flatMap(product -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(product)));
        */
		return productService.getProductById(id);
	}

}
