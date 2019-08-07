package com.epam.course.springboot.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.course.springboot.entity.Product;
import com.epam.course.springboot.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public Flux<Product> getAllProducts() {
		List<Product> products = (List<Product>) productRepository.findAll();
		/* List<Product> products = productDAO.getAllProducts(); */

		return Flux.fromIterable(productRepository.findAll());
	}

	public Mono<Product> getProductById(Long id) {
		return Mono.just(productRepository.findById(id)
				.orElseThrow(NoSuchElementException::new));
		// return productRepository.findById(id);
	}

	public Mono<Product> save(Product product) {
		return Mono.just(productRepository.save(product));
	}

	public Mono<Void> deleteById(Long id) {
		productRepository.deleteById(id);
		return Mono.empty();
	}
}
