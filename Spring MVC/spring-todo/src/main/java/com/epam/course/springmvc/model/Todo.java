/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.course.springmvc.model;

import java.io.Serializable;

/**
 * 
 * @author Shashanker_Vaduka
 *
 */
public class Todo implements Serializable{

	private Long todoId;
	private Long userId;
	private String month;
	private String day;
	private String year;
	private String title;
	private String description;
	private String priority;

	public Todo() {
	}

	public Long getTodoId() {
		return todoId;
	}

	public void setTodoId(Long todoId) {
		this.todoId = todoId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "TodoForm{" + "todoId=" + todoId + ", userId=" + userId
				+ ", month=" + month + ", day=" + day + ", year=" + year
				+ ", title=" + title + ", description=" + description
				+ ", priority=" + priority + '}';
	}

}
