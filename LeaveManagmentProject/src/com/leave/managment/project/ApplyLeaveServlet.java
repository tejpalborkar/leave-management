package com.leave.managment.project;

import java.io.IOException;
import java.io.PrintWriter;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.leave.management.project.database.DepartmentDao;
import com.leave.management.project.database.LeaveDao;
import com.leave.managment.usermodel.Department;
import com.leave.managment.usermodel.Leave;
import com.leave.managment.usermodel.LeaveStatus;
import com.leave.managment.usermodel.User;

@WebServlet("/apply-leave")
public class ApplyLeaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ApplyLeaveServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String typeOfLeave = request.getParameter("typeOfLeave");
		String leaveFrom = request.getParameter("leaveFrom");
		String leaveTo = request.getParameter("leaveTo");
		String reason = request.getParameter("reason");

		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");

		Integer departmentId = loggedInUser.getDepartment();

		DepartmentDao departmentDao = new DepartmentDao();
		// Find HOD User id from department Id
		Department department = departmentDao.getDepartmentByDeptId(departmentId);
		Leave leave = new Leave();
		leave.setLeaveType(typeOfLeave);
		leave.setFromDate(leaveFrom);
		leave.setToDate(leaveTo);

		leave.setReason(reason);
		leave.setUserId(loggedInUser.getId());
		leave.setStatus(LeaveStatus.NEW);
		
		
		leave.setHodUserId(department.getHodUserId());
		LeaveDao leaveDao = new LeaveDao();

		leaveDao.save(leave);

		response.sendRedirect("leave-status.jsp");

	}
}
