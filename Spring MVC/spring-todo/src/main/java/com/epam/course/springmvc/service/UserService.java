
package com.epam.course.springmvc.service;

import com.epam.course.springmvc.domain.Login;
import com.epam.course.springmvc.model.Todo;
import com.epam.course.springmvc.model.User;

/**
 * 
 * @author Shashanker_Vaduka
 *
 */
public interface UserService {
    public void register(User u);
    
    public void saveTodo(Todo t);

	public User login(User u);
      
}
