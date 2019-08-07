package com.epam.course.springboot;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.epam.course.springboot.entity.User;
import com.epam.course.springboot.repository.UserRepository;


@Component
@Order(1)
public class UsersBootStrap implements ApplicationRunner{
	
	private static Logger log = Logger.getLogger(UsersBootStrap.class.getName());

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		log.info("BootStrapping User Data");
		User user1 = new User("shashanker",passwordEncoder.encode("vaduka"),"ADMIN");
		User user2 = new User("user", passwordEncoder.encode("user"),"USER");
		User user3 = new User("agent", passwordEncoder.encode("agent"), "AGENT");
		
		/*User user1 = new User("shashanker","vaduka","ADMIN");
		User user2 = new User("user", "user","USER");
		User user3 = new User("agent", "agent", "AGENT");*/
		
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		
		userRepository.findAll();
		
		log.info("BootStrapping User Data done");
	}

}
