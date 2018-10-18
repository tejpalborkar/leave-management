package com.leave.managment.project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.leave.management.project.database.DepartmentDao;
import com.leave.management.project.database.UserDao;
import com.leave.managment.usermodel.Roles;

/**
 * Servlet implementation class DeleteEmployee
 */
@WebServlet("/promote-hod")
public class PromoteHod extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PromoteHod() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("userId");
		String deptId = request.getParameter("departmentId");

		Integer userId = Integer.parseInt(id);
		Integer departmentId = Integer.parseInt(deptId);

		UserDao employeeDao = new UserDao();
		employeeDao.updateRole(userId, Roles.HOD);

		DepartmentDao departmentDao = new DepartmentDao();
		departmentDao.updateDepartmentHOD(departmentId, userId);
		response.sendRedirect("admin.jsp");
	}

}
