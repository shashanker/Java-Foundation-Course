package com.epam.course.springmvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.course.springmvc.entity.Purchases;
import com.epam.course.springmvc.entity.Stock;
import com.epam.course.springmvc.service.PurchaseService;
import com.epam.course.springmvc.service.StockService;

@Controller
public class PurchaseController {

	@Autowired
	StockService stockService;

	@Autowired
	PurchaseService purchaseService;

	@PostMapping("/addToCart/{id}/{quantity}")
	public String addToCart(@PathVariable int id,
			@PathVariable(name = "quantity") int quantity,
			HttpServletRequest req) {
		HttpSession session = req.getSession();
		System.out.println("Added to Cart");
		Stock stock = stockService.getStockById((long) id);
		String username = getCurrentUser();
		Purchases purchase = new Purchases(stock, (long) quantity,
				stock.getPrice() * quantity, username, "Not-ordered");
		int i = purchaseService.addPurchase(purchase);
		Integer cart = null;
		if (i != 0) {
			if ((cart = (Integer) session.getAttribute("cart")) == null) {
				cart = 1;
			} else {
				cart = cart + 1;
			}
		}

		/*
		 * int count = 0; for (Entry<Product, Integer> elem : cart.entrySet()) {
		 * if (elem.getKey().getId() == product.getId()) { count =
		 * elem.getValue(); product = elem.getKey(); } } cart.put(product,
		 * ++count);
		 */
		System.out.println("Cart == " + cart);
		session.setAttribute("cart", cart);
		return "redirect:/";
	}

	@RequestMapping(value = "/showPurchases", method = RequestMethod.GET)
	public ModelAndView showPurchases(HttpServletRequest req) {

		HttpSession session = req.getSession();
		Integer cart = (Integer) session.getAttribute("cart");
		ModelAndView modelAndView = new ModelAndView();
		String username = getCurrentUser();
		List<Purchases> purchases = purchaseService.getAllPurchases(username);
		modelAndView.addObject("purchases", purchases);
		modelAndView.addObject("cart", cart);
		modelAndView.setViewName("purchasesView");
		return modelAndView;
	}

	@RequestMapping(value = "/orderView/{id}", method = RequestMethod.GET)
	public ModelAndView orderView(@PathVariable(name = "id") long id,
			HttpServletRequest request) {

		Purchases purchase = purchaseService.findById(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("purchase", purchase);
		modelAndView.setViewName("orderView");
		return modelAndView;
	}

	@RequestMapping(value = "/orderPurchase/{id}", method = RequestMethod.POST)
	public ModelAndView orderPurchase(@PathVariable(name = "id") long id,
			HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();

		int i = purchaseService.orderPurchase(id);
		if (i != 0) {
			modelAndView.addObject("msg", "Order Submitted");
		} else {
			modelAndView.addObject("msg", "Error : Not able to Submit Order");
		}
		modelAndView.setViewName("thankCustomer");
		return modelAndView;
	}

	
	@RequestMapping(value = "/purchaseRemove/{id}", method = RequestMethod.POST)
	public ModelAndView removePurchase(@PathVariable(name = "id") long id,
			HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();

		int i = purchaseService.removePurchase(id);
		if (i != 0) {
			modelAndView.addObject("msg", "Order Deleted");
		} else {
			modelAndView.addObject("msg", "Error : Not able to Delete Order");
		}
		modelAndView.setViewName("redirect:/showPurchases");
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/editPurchase/{id}", method = RequestMethod.GET)
	public ModelAndView editPurchaseView(@PathVariable long id) {
		
		ModelAndView modelAndView = new ModelAndView();
		Purchases purchase = purchaseService.findById(id);
		modelAndView.addObject("purchase", purchase);
		modelAndView.setViewName("editPurchaseView");
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/editPurchase", method = RequestMethod.POST)
	public ModelAndView editPurchase(@ModelAttribute("purchase") Purchases purchase) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("ID == "+purchase.getId());
		int i = purchaseService.editPurchase(purchase);
		modelAndView.setViewName("redirect:/showPurchases");
		return modelAndView;
	}
	
	public String getCurrentUser() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {

			username = ((UserDetails) principal).getUsername();

		} else {

			username = principal.toString();

		}
		return username;
	}

}
