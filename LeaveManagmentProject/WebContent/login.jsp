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
	border: 3px solid #f1f1f1;
}

input[type=text], input[type=password] {
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
	width: 10%;
	border-radius: 10%;
}

.size-container {
	width: 300px;
	height: 400px;
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
		<form action="/LeaveManagmentProject/login-servlet" method="post">
			<div class="imgcontainer">
				<h2>Login Form</h2>
				<img src="./resources/images/logo3.jpg" alt="Avatar" class="avatar">
			</div>

			<div class="size-container">

				<div class="container">

					<input type="text" placeholder="Enter Username" name="userName"
						required> <input type="password"
						placeholder="Enter Password" name="password" required>

					<button type="submit">Login</button>
				<!--	<label> <input type="checkbox" checked="checked"
						name="remember"> Remember me
						
					</label> -->
				</div>

				<div class="container" style="background-color: #f1f1f1">
						<a href="register.jsp">Register Here</a>
					 <a href="#"> Forgot password?</a>
				</div>
				
			</div>
		</form>
	</div>
</body>
</html>