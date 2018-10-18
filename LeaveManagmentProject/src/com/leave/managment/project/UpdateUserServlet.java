package com.leave.managment.project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.leave.management.project.database.UserDao;
import com.leave.managment.usermodel.Roles;
import com.leave.managment.usermodel.User;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update-user")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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

		// out.println("parameters of user pojo: "+user);

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
			User loggedInUser = userDao.getUserDetailsByUserId(Integer.parseInt(id));
			session.setAttribute("loggedInUser", loggedInUser);

			if (loggedInUser == null) {
				response.sendRedirect("login.jsp");
			} else {
				if (Roles.HOD.equals(loggedInUser.getRole())) {
					response.sendRedirect("hod.jsp");
				} else if (Roles.STAFF.equals(loggedInUser.getRole())) {
					response.sendRedirect("leave-status.jsp");
				}
				if (Roles.ADMIN.equals(loggedInUser.getRole())) {
					response.sendRedirect("admin.jsp");
				}

			}
		}
	}
}
