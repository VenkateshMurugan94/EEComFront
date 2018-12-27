<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.login-sec {
	padding: 50px 30px;
	position: relative;
}

.login-sec h2 {
	margin-bottom: 30px;
	font-weight: 800;
	font-size: 30px;
	color: #0069c0;
}

.login-sec h2:after {
	content: " ";
	width: 100px;
	height: 5px;
	background: #6ec6ff;
	display: block;
	margin-top: 20px;
	border-radius: 3px;
	margin-left: auto;
	margin-right: auto
}

.btn-login {
	background: #0069c0;
	color: #fff;
	font-weight: 600;
	margin: 30px;
}
</style>

<div class="container">

	<c:if test="${haserror}">
		<div class="alert alert-danger">
			<strong>${error}</strong>
		</div>
	</c:if>
	
	<div class="row ">
		<div class="col login-sec">
			<h2 class="text-center">Registration Form</h2>
			
			<form:form class="login-form" modelAttribute="UserDetail" action="CreateUser" method="post">
			
			<c:if test="${editmode}">
					<div class="form-group">
						<form:input type="text" class="form-control" placeholder="" path="cartId" readonly="true" />
						<form:errors path="cartId" cssStyle="color:red"></form:errors>
					</div>
				</c:if>
				
				<div class="form-group">
					<label for="exampleInputEmail1" class="text-uppercase">User
						Name</label>
						 <form:input type="text" class="form-control" placeholder="" path="userName"/>
						 <form:errors path="userName" cssStyle="color:red"></form:errors>
				</div>
				
				
				
				<div class="form-group">
					<label for="exampleInputPassword1" class="text-uppercase">Email
						Id</label> 
						<form:input type="text" class="form-control" placeholder="" path="emailId"/>
						<form:errors path="emailId" cssStyle="color:red"></form:errors>
				</div>
				
				
				<div class="form-group">
					<label for="exampleInputPassword1" class="text-uppercase">Mobile
						No</label> 
						<form:input type="text" class="form-control" placeholder="" path="mobileNo"/>
						<form:errors path="mobileNo" cssStyle="color:red"></form:errors>
				</div>
				
				
				<div class="form-group">
					<label for="exampleInputPassword1" class="text-uppercase">Password</label>
					<form:input type="password" class="form-control" placeholder="" path="password"/>
					<form:errors path="password" cssStyle="color:red"></form:errors>
				</div>
				

				<div class="form-check">
						<input type="submit" class="btn btn-login float-right"
							value="Add Register">
				</div>

			</form:form>
		</div>
	</div>
</div>