<form method="post" action="/LeaveManagmentProject/update-user">
			 <input  type="hidden" value ="${loggedInUser.id}" name="id" />
				<div style="margin: 10px;">
					<div class="row">
			 			<label class="col-xs-2">First Name </label>
			 			<input   type="text"  value="${loggedInUser.firstName}" name="firstName"required />
					</div>

					<div class="row" style="text-align:left;">
						<label class="col-xs-2">Last Name </label>
						<input  class="col-xs-2" type="text" value ="${loggedInUser.lastName}"  name="lastName"required />
					</div>

					<div class="row">
						<label class="col-xs-2">Department</label>
						<select class="col-xs-2" name = "department" value="${loggedInUser.department}" id = "department" >
								<option  value="0">Select Department</option>
								<option id="departmentId2" value="2">Electronics And Telecom</option>
								<option id="departmentI3" value="3">Computer</option>
								<option id="departmentId4" value="4">IT</option>
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

		$("#departmentId${loggedInUser.departmentId}").prop('selected', true);

	});
</script>
			
			     
