package com.leave.managment.project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.leave.management.project.database.UserDao;
import com.leave.managment.usermodel.Roles;
import com.leave.managment.usermodel.User;

@WebServlet("/register-servlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String department = request.getParameter("department");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		String id = request.getParameter("id");
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUserName(userName);
		user.setPassword(password);
		user.setDepartment(Integer.parseInt(department));
		user.setRole(Roles.STAFF);

		// out.println("parameters of user pojo: "+user);
System.out.println("user to update:"+user);
		UserDao userDao = new UserDao();

		if (id != null && id.length() != 0) {
			user.setId(Integer.parseInt(id));
			userDao.updateUser(user);
		} else {
			userDao.save(user);
		}

		HttpSession session = request.getSession();
		if (session == null) {
			response.sendRedirect("login.jsp");
		} else {

			User loggedInUser = (User) session.getAttribute("loggedInUser");
			if (loggedInUser == null) {
				response.sendRedirect("login.jsp");
			} else {
				response.sendRedirect("admin.jsp");
			}
		}
	}
}
