package com.epam.course.springboot.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.epam.course.springboot.entity.Property;
import com.epam.course.springboot.enums.Status;

public interface PropertyRepository extends CrudRepository<Property, Serializable>{
	
	Property findById(Long id);
	
	@Query("select p from Property p where p.status= :status")
    List<Property> findAvaliableProperty(@Param( "status" ) Status status);
}
