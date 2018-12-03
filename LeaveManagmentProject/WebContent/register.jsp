<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Leave Management System</title>

<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

form {
/* 	border: 3px solid #f1f1f1; */
}


input[type=text], input[type=password] {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}


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
	border: 1px solid #ccc;
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

.cancelbtn {
	width: auto;
	padding: 10px 18px;
	background-color: #f44336;
}

.imgcontainer {
	text-align: center;
	margin: 24px 0 12px 0;
}

img.avatar {
	width: 3%;
	border-radius: 10%;
}

.size-container {
	width: 400px;
	height: 500px;
}

.container {
	padding: 10px;
}

span.psw {
	float: right;
	padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
	span.psw {
		display: block;
		float: none;
	}
	.cancelbtn {
		width: 100%;
	}
}
</style>
</head>
<body>


	<div align="center">
		<form method="post" action="/LeaveManagmentProject/register-servlet">
			<div class="imgcontainer">
				<h2>Registration Form</h2>
				<img src="./resources/images/logo2.jpg" alt="Avatar" class="avatar">
			</div>

			<div class="size-container">

				<div class="container">

					<input type="text" placeholder="Enter First name" name="firstName"required />
					<input type="text" placeholder="Enter Last name" name="lastName"required />
<!-- 					 <input type="password" placeholder="Enter Email" name="email" required />  -->
					 	
					<select name = "department" id = "department" >
							<option value="0">Select Department</option>
							<option id="departmentId2" value="2">Computer</option>
							<option id="departmentId3" value="3">Civil</option>
							<option id="departmentId4" value="4">Mechanical</option>
							<option id="departmentId5" value="5">IT</option>
							<option id="departmentId6" value="6">E&TC</option>
					</select>
					 <input type="text"placeholder="Enter Username" name="userName" required />
					 <input type="password" placeholder="Enter Password" name="password" required />
					 <input type="password" placeholder="Confirm Password" name="confirmPassword" required />

					<button type="submit">Register</button>

					<button style="background-color: blue;" onclick="window.location.href='login.jsp'">Already Registered?</button>
				
				</div>

			</div>
		</form>
	</div>
</body>
</html>