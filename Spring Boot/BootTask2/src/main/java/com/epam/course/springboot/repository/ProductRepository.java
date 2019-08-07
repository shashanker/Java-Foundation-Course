package com.epam.course.springboot.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.epam.course.springboot.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Serializable>{

	Product findById(Long id);
}
