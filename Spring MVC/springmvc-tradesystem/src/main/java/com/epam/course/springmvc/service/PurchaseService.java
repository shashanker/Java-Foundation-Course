package com.epam.course.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.course.springmvc.entity.Purchases;
import com.epam.course.springmvc.repository.PurchasesDAO;

@Service
public class PurchaseService {

	@Autowired
	PurchasesDAO purchasesDAO;
	
	public int addPurchase(Purchases purchase) {
		// TODO Auto-generated method stub
		return purchasesDAO.save(purchase);
	}

	public List<Purchases> getAllPurchases(String username) {
		// TODO Auto-generated method stub
		return purchasesDAO.findAllPurchasesByUser(username);
		
	}

	public int orderPurchase(long id) {
		// TODO Auto-generated method stub
		
		return purchasesDAO.orderPurchase(id);
		
	}

	public Purchases findById(long id) {
		// TODO Auto-generated method stub
		return purchasesDAO.findById(id);
	}

	public int removePurchase(long id) {
		// TODO Auto-generated method stub
		return purchasesDAO.removeById(id);
	}

	public int editPurchase(Purchases purchase) {
		// TODO Auto-generated method stub
		return purchasesDAO.edit(purchase);
		
	}

	
}
