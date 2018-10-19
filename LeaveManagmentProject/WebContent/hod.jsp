<%@page import="com.leave.managment.usermodel.LeaveStatus"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.leave.managment.usermodel.Leave"%>
<%@page import="java.util.List"%>
<%@page import="com.leave.managment.usermodel.User"%>
<%@page import="com.leave.management.project.database.LeaveDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Leave Management System</title>


<center><h2>Leave management system</h2></center>
</head>


<%

LeaveDao leaveDao = new LeaveDao();
User user =(User)session.getAttribute("loggedInUser");
List<Leave> leaves = leaveDao.getLeavesByUserId(user.getId());

pageContext.setAttribute("leaves", leaves);

List<Leave> newLeaves = leaveDao.getLeavesByStatusAndHodUser(user.getId(), LeaveStatus.NEW);
pageContext.setAttribute("newLeaves", newLeaves);
List<Leave> approvedAndRejected = leaveDao.getApprovedAndRejectedAndHodUser(user.getId());
pageContext.setAttribute("approvedAndRejected", approvedAndRejected);

%>

<body style="background-color: #eaefeac7;">

	<span class="welcome">Welcome,${loggedInUser.firstName}</span>
	<div class="container" style="margin-top:50px;">
		<ul class="nav nav-tabs">
			<li class="active" style="margin-left:5px;"><a data-toggle="tab" href="#applyLeave">Leave
					Requests</a></li>
			<li  style="margin-left:5px;"><a data-toggle="tab" href="#leaveHistory">History</a></li>
			<li  style="margin-left:5px;"><a data-toggle="tab" href="#myprofile">My Profile</a></li>
			<li style="float:right;margin-right: -115px;"><a  href="logout-servlet">Logout</a></li>
		</ul>

	<div class="tab-content">
   		 <div id="applyLeave" class="tab-pane fade in active">
		<table class="table">
		    <thead>
		      <tr>
		      <th>Sr. No</th>
		     	<th>Applicant</th>
		        <th>Leave From</th>
		        <th>Leave To</th>
		        <th>Applied on</th>
		        <th>Reason</th>
		        <th>Status</th>
		        <th>Action</th>
		      </tr>
		    </thead>
		    <tbody>
	      
	      <c:forEach var="leave" items="${newLeaves}" varStatus="id">
				<tr>
				<td>${id.count}</td>
				<td>${leave.applicantName}</td>
					<td>${leave.fromDate}</td>
					<td>${leave.toDate}</td>
					<td>${leave.appliedDate}</td>
					<td>${leave.reason}</td>
					<td>${leave.status}</td>
					<td   class="col-xs-3">
					<a style="width:90px; padding:0px;" class="btn btn-primary" role="button" href="<%=application.getContextPath()%>/leave-action?action=approve&leaveId=${leave.leaveId}">Approve</a>
					<a style="width:90px; padding:0px;" class="btn btn-danger" role="button"href="<%=application.getContextPath()%>/leave-action?action=reject&leaveId=${leave.leaveId}">Reject</a>
				</td>
				</tr>
				<p>
		</c:forEach>
	      
	      
	    </tbody>
	  </table>
 </div>
   
   		 <div id="leaveHistory" class="tab-pane fade ">
     <div class="container">
  
		  <table class="table">
		    <thead>
		      <tr>
		      <th>Sr. No</th>
		      <th>Applicant</th>
		        <th>Leave From</th>
		        <th>Leave To</th>
		        <th>Applied on</th>
		        <th>Reason</th>
		        <th>Status</th>
		      </tr>
		    </thead>
		    <tbody>
		         
		      
		      <c:forEach var="leave" items="${approvedAndRejected}" varStatus="id">
					<tr>
					<td>${id.count}</td>
					<td>${leave.applicantName}</td>
						<td>${leave.fromDate}</td>
						<td>${leave.toDate}</td>
						<td>${leave.appliedDate}</td>
						<td>${leave.reason}</td>
						<td>${leave.status}</td>
						
					</tr>
					<p>
			</c:forEach>
		      
		      
		    </tbody>
		  </table>
		</div>
    </div>
   
	    <div id="myprofile" class="tab-pane fade ">
			    <jsp:include page="edit-user.jsp"></jsp:include> 
	 	</div>
   
    </div>
</div>
</body>
<style>

select {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

a {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	box-sizing: border-box;
}


.form {width =300px;height =400px;
	
}

button {
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
}

button:hover {
	opacity: 0.8;
}


button {
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
}



li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

li a:hover {
	background-color: #111;
}

[type="date"] {
	background: #fff
		url(https://cdn1.iconfinder.com/data/icons/cc_mono_icon_set/blacks/16x16/calendar_2.png)
		97% 50% no-repeat;
}

[type="date"]::-webkit-inner-spin-button {
	display: none;
}

[type="date"]::-webkit-calendar-picker-indicator {
	opacity: 0;
}

/* custom styles */
body {
	padding: 4px;
	background: #e5e5e5;
	font: 13px/1.4 Geneva, 'Lucida Sans', 'Lucida Grande',
		'Lucida Sans Unicode', Verdana, sans-serif;
}

.nav{
background-color:black;
}

label {
	display: block;
	text-align:left;
}

input {
	border: 1px solid #c4c4c4;
	border-radius: 5px;
	background-color: #fff;
	padding: 3px 5px;
	box-shadow: inset 0 3px 6px rgba(0, 0, 0, 0.1);
	width: 190px;
}

<!--
css for date -->legend {
	background-color: #000;
	color: #fff;
	padding: 3px 6px;
}

.output {
	font: 1rem 'Fira Sans', sans-serif;
}

input {
	margin: .4rem;
}

.welcome{
	color: black;
	text-align: right;
	padding: 14px 16px;
	text-decoration: none;
	float:right;
	direction:rtl;
	margin-right: 95px;
}
</style>

<script>
	$(document).ready(function() {

		$("#departmentId${loggedInUser.departmentId}").prop('selected', true);

	});
</script>

</html>