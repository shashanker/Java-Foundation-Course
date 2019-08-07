package com.epam.course.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.course.springboot.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
