<form method="post" action="/LeaveManagmentProject/update-user">
			 <input  type="hidden" value ="${loggedInUser.id}" name="id" />
				<div style="margin: 10px;">
					<div class="row">
			 			<label class="col-xs-2">First Name </label>
			 			<input   type="text"  value="${loggedInUser.firstName}" name="firstName"required />
					</div>

					<div class="row" >
						<label class="col-xs-2">Last Name </label>
						<input   type="text" value ="${loggedInUser.lastName}"  name="lastName"required />
					</div>

					<div class="row">
						<label class="col-xs-2">Department</label>
						<select class="col-xs-2" name = "department" value="${loggedInUser.department}" id = "department" >
								<option  value="0">Select Department</option>
								<option id="departmentId2" value="2">Computer</option>
								<option id="departmentId3" value="3">Civil</option>
								<option id="departmentId4" value="4">Mechanical</option>
								<option id="departmentId5" value="5">IT</option>
								<option id="departmentId6" value="6">E&TC</option>
						</select>
					</div>
					
					<div class="row">
						<label class="col-xs-2">User name </label>
						 <input class="col-xs-2" type="text" value="${loggedInUser.userName}" name="userName" required />
					</div>
					
					<div class="row">
						<label class="col-xs-2">Password </label>
						 <input class="col-xs-2" type="password" value="${loggedInUser.password}" name="password" required />
					</div>
					
					
					<div class="row">
						<span  class="col-xs-2"></span>
						<button class="col-xs-2"  type="submit">Update</button>
					</div>
				</div>
			</form>
			
			
			
<script>
	$(document).ready(function() {
console.log('#departmentId${loggedInUser.departmentId}')
		$("#departmentId${loggedInUser.departmentId}").prop('selected', true);

	});
</script>
			
			     
