package com.leave.managment.project;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.leave.management.project.database.LeaveDao;
import com.leave.managment.usermodel.LeaveStatus;


@WebServlet("/leave-action")
public class LeaveActionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public LeaveActionsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String id =request.getParameter("leaveId");
		String action =request.getParameter("action");
		
		Integer leaveId= Integer.parseInt(id);
		LeaveDao leaveDao = new LeaveDao();
		if("approve".equals(action)) {
			leaveDao.updateStatus(LeaveStatus.APPROVED, leaveId);
		}
		if("reject".equals(action)) {
			leaveDao.updateStatus(LeaveStatus.REJECTED, leaveId);
		}
		
		response.sendRedirect("hod.jsp");
	}

}
