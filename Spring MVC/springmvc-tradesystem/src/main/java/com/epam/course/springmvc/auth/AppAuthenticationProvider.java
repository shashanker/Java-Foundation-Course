package com.epam.course.springmvc.auth;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.epam.course.springmvc.config.LoggerInterceptor;
import com.epam.course.springmvc.entity.User;
import com.epam.course.springmvc.exception.NotAuthenticatedException;
import com.epam.course.springmvc.repository.UserDAO;
import com.epam.course.springmvc.auth.UserDetailsCustom;
public class AppAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserDAO userDAO;
	

	// The logger we use in this class
	private static Logger log = Logger.getLogger(LoggerInterceptor.class.getName());

	/**
	 * Checks username and password with the stuff that's in the db.
	 *
	 * @param authentication
	 *            the authentication provided by the user
	 * @return auth, if authenticated. Otherwise null.
	 * @throws AuthenticationException
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		log.log(Level.INFO, "inside authenticate", "");
		String userName = authentication.getName().trim();
		String password = authentication.getCredentials().toString().trim();

		User user = userDAO.findByUserName(userName);

		if (user == null) {
			throw new NotAuthenticatedException("Invalid_UserName_Password");
		} 
		try {
			if (password.equals(user.getPassword())) {
				UserDetailsCustom userDetailsCustom = new UserDetailsCustom(user);
				authentication = new UsernamePasswordAuthenticationToken(userDetailsCustom, password,
						userDetailsCustom.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
				return authentication;
			} else {
				//throw new WebApplicationException(Response.status(Status.BAD_REQUEST)
					//	.entity(new CDResponse(cdUtil.getMessage("Invalid_UserName_Password"))).build());
				
				throw new NotAuthenticatedException("Invalid_UserName_Password");
			}
		} catch (Exception e) {
			log.log(Level.SEVERE,e.getMessage(), e);
			throw new NotAuthenticatedException("Unable to check password!", e);
					
		}
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}