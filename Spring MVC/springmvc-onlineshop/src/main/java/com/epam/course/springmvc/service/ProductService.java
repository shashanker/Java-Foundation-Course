package com.epam.course.springmvc.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.course.springmvc.dao.ProductDAO;
import com.epam.course.springmvc.model.Product;

@Service
public class ProductService {

	@Autowired
	ProductDAO productDAO;

	public List<Product> getAllProducts() {
		List<Product> products = productDAO.getAllProducts();
		return products;
	}

	public Product getProductById(int id) {
		return productDAO.getProductById(id);
	}
}
