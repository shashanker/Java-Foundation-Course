package com.epam.course.hibernate.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class Person {
 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @Column(unique=true)
	@NotNull
    private String name;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
 
    // constructor, getters, setters
    
}