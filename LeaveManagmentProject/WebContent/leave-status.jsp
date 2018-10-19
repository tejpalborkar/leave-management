
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

</head>


<%

LeaveDao leaveDao = new LeaveDao();
User user =(User)session.getAttribute("loggedInUser");
List<Leave> leaves = leaveDao.getLeavesByUserId(user.getId());

pageContext.setAttribute("leaves", leaves);

%>
<center><h2>Leave management system</h2></center>
<body style="background-color:#eaefeac7;">
<span class="welcome"><b>Welcome, ${loggedInUser.firstName}</b></span>



<div class="container" style="margin-top:50px;">
		<ul class="nav nav-tabs">
			<li class="active" style="margin-left:5px;"><a data-toggle="tab" href="#leaveHistory" >My Leaves</a></li>
			<li  style="margin-left:5px;"><a data-toggle="tab" href="#applyLeave">Apply for Leave</a></li>
			<li  style="margin-left:5px;"><a data-toggle="tab" href="#myprofile">My Profile</a></li>
			<li style="float:right; margin-right: -115px;"><a href="logout-servlet">Logout</a></li>
		</ul>
  <div class="tab-content">
    <div id="applyLeave" class="tab-pane fade">
     <form method="post" action="<%=request.getContextPath() %>/apply-leave">
      <table>
				<br/><br/>
					<tr>
					<td>Select leave type</td>
					<td colspan="1">
					<select name = "typeOfLeave" id = "typeOfLeave">
							<option value="0">Select leave type</option>
							<option value="1">Maternity</option>
							<option value="2">sick</option>
							<option value="3">Causual</option>
							<option value="4">Earned /PL</option>
					</select>
					</td></tr>

				<!-- 	<tr><td>Comment</td><td><textarea rows="2" cols="20" name="comment"> </textarea></td></tr> -->
					<tr><td>Leave from</td><td><input type="date" name="leaveFrom" style="width:350px; "></td></tr>
					<tr><td>Leave to</td> <td><input type="date" name="leaveTo" style="width:350px; "></td></tr>
					<tr><td>Description</td> <td><textarea name="reason" style="width:350px; height:115px;" ></textarea></td></tr>
					<tr><td><button style="background-color: blue;" type="submit" name="submit">Apply For leave</button></td></tr>
      </table>  
      </form>
    </div>
   

    <div id="leaveHistory" class="tab-pane fade in active">
     <div class="container">
  
  <table class="table table-hover">
    <thead>
      <tr>
      <th>Sr. No</th>
        <th>Leave From</th>
        <th>Leave To</th>
        <th>Applied on</th>
        <th>Reason</th>
        <th>Status</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody>
         
      
      <c:forEach var="leave" items="${leaves}" varStatus="id">
			<tr>
			<td>${id.count}</td>
				<td>${leave.fromDate}</td>
				<td>${leave.toDate}</td>
				<td>${leave.appliedDate}</td>
				<td>${leave.reason}</td>
				<td>${leave.status}</td>
				<td>
				<a  class="btn btn-danger" role="button" style="width:70px; padding:0px;" href="<%=application.getContextPath()%>/delete-leave?leaveId=${leave.leaveId}">Delete</a>
<%-- 				<a  class="btn btn-primary" role="button" style="width:70px;padding:0px;" href="<%=application.getContextPath()%>/update-leave?leaveId=${leave.leaveId}">Edit</a> --%>
			</td>
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

</html>