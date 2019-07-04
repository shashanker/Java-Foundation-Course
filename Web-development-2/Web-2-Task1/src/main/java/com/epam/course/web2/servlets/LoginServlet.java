package com.epam.course.web2.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * LoginServlet.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	/**
	 * doGet(HttpServletRequest req, HttpServletResponse resp).
	 *
	 * return content of Login
	 */
	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		pw.println("<center><h2>Welcome to the WebApp</h2></center>");
	}

}
