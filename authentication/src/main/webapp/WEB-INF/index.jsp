<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Authentication</title>
    
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div>
		<div>
			<div>
				<h1>Welcome!</h1>
			</div>
			<div>
				<h2>Join our growing community.</h2>
			</div>
			<div>
				<h1>Register</h1>
			</div>
			<form:form action="/register" method="post" modelAttribute="newUser">
				<div>
					<form:label path="userName">Username</form:label><br/>
					<form:input type="text" path="userName"/>
					<form:errors path="userName" class="text-danger"></form:errors>
				</div>
				
				<div>
					<form:label path="email">Email</form:label><br/>
					<form:input type="email" path="email"/>
					<form:errors path="email" class="text-danger"></form:errors>
				</div>
				
				<div>
					<form:label path="password">Password</form:label><br/>
					<form:input type="password" path="password"/>
					<form:errors path="password" class="text-danger"></form:errors>
				</div>
				
				<div>
					<form:label path="confirm">Confirm PW</form:label><br/>
					<form:input type="password" path="confirm"/>
					<form:errors path="confirm" class="text-danger"></form:errors>
				</div>
				<div>
					<input class="btn btn-primary" type="submit" value="Submit"/>
				</div>
			</form:form> 
		</div>
		<div>
			<div>
				<h1>Log in</h1>
			</div>
			<div>
				<form:form action="/login" method="post" modelAttribute="newLogin">
				<form:errors path="email" class="text-danger"></form:errors>
					<div>
						<form:label path="email">Email</form:label><br/>
						<form:input type="email" path="email"/>
					</div>
					<div>
						<form:label path="password">Password</form:label><br/>
						<form:input type="password" path="password"/>
					</div>
					<div>
						<input class="btn btn-primary" type="submit" value="Submit"/>
					</div>
				</form:form> 
			</div>
		</div>
	</div>
</body>
</html>