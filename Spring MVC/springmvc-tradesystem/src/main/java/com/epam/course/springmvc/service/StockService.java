package com.epam.course.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.course.springmvc.entity.Stock;
import com.epam.course.springmvc.repository.StockDAO;

@Service
public class StockService {
	
	
	@Autowired
	StockDAO stockDAO;

	public List<Stock> getAllStocks() {
		List<Stock> stocks = stockDAO.getAllStocks();
		return stocks;
	}

	public Stock getStockById(Long id) {
		return stockDAO.findById(id);
	}

}
