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
    <title>Book</title>
    
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div>
		<h1><c:out value="${book.title}"/></h1>
	</div>
	<div>
		<h3><c:out value="${book.user.name}"/> read <c:out value="${book.title}"/> by <c:out value="${book.author}"/></h3>
	</div>
	<div>
		<h3>Here are <c:out value="${book.user.name}'s"/> thoughts:</h3>
		<hr/>
		<p><c:out value="${book.thoughts}"/></p>
		<hr/>
	</div>
	<div>
		<button class="btn-warning"><a href="/books/${book.id}/edit">Edit</a></button>
		<form action="/books/${book.id}/delete" method="POST">
			<input type="hidden" name="_method" value="DELETE"/>
			<input type="submit" value="Delete" class="btn btn-danger"/>
		</form>
	</div>
</body>
</html>