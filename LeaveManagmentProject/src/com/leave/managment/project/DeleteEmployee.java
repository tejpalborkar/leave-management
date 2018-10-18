package com.leave.managment.project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.leave.management.project.database.UserDao;


/**
 * Servlet implementation class DeleteEmployee
 */
@WebServlet("/delete-user")
public class DeleteEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String id =request.getParameter("userId");
		
		Integer userId= Integer.parseInt(id);
		
		UserDao employeeDao = new UserDao();
		employeeDao.deleteUser(userId);
		response.sendRedirect("admin.jsp");
	}

}
