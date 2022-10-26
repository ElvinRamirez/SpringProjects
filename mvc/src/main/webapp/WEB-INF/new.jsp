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
    <title>Books</title>
    
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<h1>New Book</h1>
		<form:form action="/new" method="post" modelAttribute="book" class="border border-primary w-25 p-3 p-3 mb-2 bg-secondary text-white">
			<div>
				<form:label path="title">Title</form:label><br/>
				<form:errors path="title" class="text-danger"/>
				<form:input type="text" path="title"/>
			</div>
			<div>
				<form:label path="description">Description</form:label><br/>
				<form:errors path="description" class="text-danger"/>
				<form:textarea path="description"/>
			</div>
			<div>
				<form:label path="language">Language</form:label><br/>
				<form:errors path="language" class="text-danger"/>
				<form:input type="text" path="language"/>
			</div>
			<div>
				<form:label path="numberOfPages">Pages</form:label><br/>
				<form:errors path="numberOfPages" class="text-danger"/>
				<form:input type="number" path="numberOfPages"/>
			</div>
			<input class="btn btn-primary" type="submit" value="Submit"/>
	</form:form>
</body>
</html>