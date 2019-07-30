package com.epam.course.springmvc.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
public class CartController {

	@Autowired
	ProductService productService;

	@PostMapping("/addToCart/{id}")
	public String addToCart(@PathVariable int id, HttpServletRequest req) {
		HttpSession session = req.getSession();
		System.out.println("Added to Cart");
		Product product = productService.getProductById(id);
		Map<Product, Integer> cart = null;
		if (session.getAttribute("cart") == null) {
			cart = new HashMap<>();
		} else {
			cart = (HashMap<Product, Integer>) session.getAttribute("cart");
		}

		int count = 0;
		for (Entry<Product, Integer> elem : cart.entrySet()) {
			if (elem.getKey().getId() == product.getId()) {
				count = elem.getValue();
				product = elem.getKey();
			}
		}
		cart.put(product, ++count);
		System.out.println("Cart == " + cart);
		session.setAttribute("cart", cart);
		return "redirect:/";
	}

	@GetMapping("/showCart")
	public ModelAndView showCart(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("cart", cart);
		modelAndView.setViewName("cartDetails");
		return modelAndView;
	}

	@GetMapping("/clearCart")
	public ModelAndView clearCart(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute("cart", null);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("cartDetails");
		return modelAndView;
	}

	@PostMapping("/cartRemove/{id}")
	public ModelAndView cartRemove(@PathVariable int id, HttpServletRequest req) {
		HttpSession session = req.getSession();
		Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
		Product removeProduct = null;
		if (cart != null) {
			for (Entry<Product, Integer> e : cart.entrySet()) {
				if (e.getKey().getId() == id) {
					removeProduct = e.getKey();
					break;
					
				}
			}
		}
		cart.remove(removeProduct);
		session.setAttribute("cart", cart);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("cart", cart);
		modelAndView.setViewName("cartDetails");
		return modelAndView;
	}
	
	
	@PostMapping("/checkout")
	public ModelAndView checkOut(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		session.setAttribute("cart", null);
		ModelAndView modelAndView = new ModelAndView();
		//modelAndView.addObject("cart", cart);
		modelAndView.setViewName("thankCustomer");
		return modelAndView;
	}

}
