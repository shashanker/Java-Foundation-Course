package com.epam.course.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.course.springboot.entity.Product;
import com.epam.course.springboot.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public List<Product> getAllProducts() {
		List<Product> products = (List<Product>) productRepository.findAll();
		/*List<Product> products = productDAO.getAllProducts();*/
		return products;
	}

	public Product getProductById(Long id) {
		return productRepository.findById(id);
	}
}
