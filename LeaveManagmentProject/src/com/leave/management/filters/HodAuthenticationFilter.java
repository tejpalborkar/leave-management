package com.leave.management.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.leave.managment.usermodel.Roles;
import com.leave.managment.usermodel.User;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter(urlPatterns = { "/hod.jsp", "/leave-action" })
public class HodAuthenticationFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public HodAuthenticationFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// pass the request along the filter chain
		System.out.println("Executing HOD authentication filter");
		HttpServletRequest servletRequest = (HttpServletRequest) request;

		HttpSession session = servletRequest.getSession();

		User user = (User) session.getAttribute("loggedInUser");
		System.out.println("Logged in user:"+user);
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		if (user == null) {
			System.out.println("User not logged in.");
			servletResponse.sendRedirect("login.jsp");

		} else {
			String role = user.getRole();
			if (role.equals(Roles.HOD)) {
				chain.doFilter(request, response);
			} else {
				servletResponse.sendRedirect("login.jsp");
			}
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
