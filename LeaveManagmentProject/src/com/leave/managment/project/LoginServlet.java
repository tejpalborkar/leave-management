package com.leave.managment.project;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.leave.management.project.database.UserDao;
import com.leave.managment.usermodel.Roles;
import com.leave.managment.usermodel.User;

@WebServlet("/login-servlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		// PrintWriter out=response.getWriter();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		@SuppressWarnings("unused")
		HttpSession session = request.getSession();
		UserDao userDao = new UserDao();

		User user = userDao.getuser(userName, password);
		// Employee employee =

		if (user == null) {

			// employee is not registered

			// show error
			response.getWriter().write("<center><p color='red'>Incorect username or password </p></center>");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");

			rd.include(request, response);
		} else {
			// Employee is registered and found in context
			String employeeOriginalPassword = user.getPassword();

			if (employeeOriginalPassword.equals(password)) {

				user = userDao.getUserDetailsByUserId(user.getId());
				HttpSession session1 = request.getSession();
				session1.setAttribute("loggedInUser", user);

				if (Roles.HOD.equals(user.getRole())) {
					response.sendRedirect("hod.jsp");
				} else if (Roles.STAFF.equals(user.getRole())) {
					response.sendRedirect("leave-status.jsp");
				}
				if (Roles.ADMIN.equals(user.getRole())) {
					response.sendRedirect("admin.jsp");
				}

			} else {
				response.getWriter().write("<center><p color='red'>Incorect username or password </p></center>");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.include(request, response);
			}

		}

	}
}