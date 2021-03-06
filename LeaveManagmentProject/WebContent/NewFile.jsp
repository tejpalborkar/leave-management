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
		<form method="post" action="/LeaveManagmentProject/login-servlet">
			<div class="imgcontainer">
				<h2>Registration Form</h2>
				<img src="./resources/images/logo2.jpg" alt="Avatar" class="avatar">
			</div>

			<div class="size-container">
				<div class="container">
					<select >
							<option value="">Select Type Of Leave</option>
								<option value="maternity">Maternity</option>
								<option value="sick">Sick</option>
								<option value="paid">Paid</option>
					</select>
					<textarea placeholder="Enter reason for leave" rows="4" cols="50" name="comment"> </textarea>
					<input  type="date" placeholder="Select Date From" name="leaveFrom">
					<input type="date" placeholder="Select Date To" name="leaveTo">

					<button type="submit">Register</button>

					<button style="background-color: blue;">Already Registered?</button>
				</div>

			</div>
		</form>
	</div>
</body>
</html>