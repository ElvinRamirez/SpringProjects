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
    <title>New Ninja</title>
    
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<h1>New Ninja</h1>
	<form:form action="/create/ninja" method="post" modelAttribute="ninja">
		<div>
			<form:label path="firstName">First Name</form:label><br/>
			<form:input type="text" path="firstName"/>
		</div>
		<div>
			<form:label path="lastName">Last Name</form:label><br/>
			<form:input type="text" path="lastName"/>
		</div>
		<div>
			<form:label path="age">Age</form:label><br/>
			<form:input path="Age"/>
		</div>
		<div>
			<form:label path="dojo">Dojo</form:label><br/>
			<form:select path="dojo">
			<option selected>Select a Dojo</option>
				<c:forEach var="dojo" items="${dojos}">
					<option value="${dojo.id}"><c:out value="${dojo.name}"/></option>
				</c:forEach>
			</form:select>
		</div>
		<input class="btn btn-primary" type="submit" value="Create"/>
	</form:form> 
</body>
</html>