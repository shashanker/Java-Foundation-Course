package com.epam.course.webtask.filter;
// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class SiteHitCounter implements Filter {

   private int hitCount; 

   public void  init(FilterConfig config) throws ServletException {
      // Reset hit counter.
      hitCount = 0;
   }

   public void  doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
      throws java.io.IOException, ServletException {

	   HttpServletRequest req = (HttpServletRequest) request;
	   HttpServletResponse res = (HttpServletResponse) response;
      // increase counter by one
      hitCount++;

      // Print the counter.
      
      //System.out.println("Site visits count :"+ hitCount );

      //Cookie[] cookies = req.getCookies();
      
      Cookie c = new Cookie("hitCount",String.valueOf(hitCount));
      res.addCookie(c);
      
      // Pass request back down the filter chain
      chain.doFilter(req,res);
   }
   
   public void destroy() { 
      // This is optional step but if you like you
      // can write hitCount value in your database.
   } 
} 