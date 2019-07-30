package com.epam.course.springmvc.dao;

import com.epam.course.springmvc.model.User;

/**
 * 
 * @author Shashanker_Vaduka
 *
 */
public interface UserDAO {

    public void save(User u);

    public void update(User u);

	public User find(User u);
}
