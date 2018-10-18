package com.leave.managment.project;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.leave.management.project.database.LeaveDao;


@WebServlet("/delete-leave")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String id =request.getParameter("leaveId");
		
		Integer leaveId= Integer.parseInt(id);
		
		
		LeaveDao leaveDao = new LeaveDao();
		leaveDao.deleteLeave(leaveId);
		response.sendRedirect("leave-status.jsp");
	}

}
