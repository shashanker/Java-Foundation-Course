package com.epam.course.springmvc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.epam.course.springmvc.H2BootStrap;
import com.epam.course.springmvc.auth.AppAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class BasicAuthConfiguration 
  extends WebSecurityConfigurerAdapter {
 
   /* @Override
    protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
        auth
          .inMemoryAuthentication()
          .withUser("user")
          .password("password")
          .roles("USER");
    }*/
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	H2BootStrap h2BootStrap;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		
	 /* auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
			"select username,password from users where username=?")
		.authoritiesByUsernameQuery(
			"select username, role from users where username=?");*/
		auth.authenticationProvider(authenticationProvider());
		
	}	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		return new AppAuthenticationProvider();
	}
 
    @Override
    protected void configure(HttpSecurity http) 
      throws Exception {
        http.csrf().disable()
          .authorizeRequests()
          .antMatchers("/login").permitAll()
          .antMatchers("/").hasAuthority("USER")
			.and()
				.formLogin().loginPage("/loginPage")
				.defaultSuccessUrl("/homePage")
				.failureUrl("/loginPage?error")
				.usernameParameter("username").passwordParameter("password")				
			.and()
				.logout().logoutSuccessUrl("/loginPage?logout")
          .and()
          .httpBasic();
    }
}