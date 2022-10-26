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
	<h1>All Books</h1>
	<table class="table">
		<thead>
			<tr class="table-dark">
				<th>ID</th>
				<th>Title</th>
				<th>Language</th>
				<th>Number of Pages</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="books" items="${books}">
			<tr class="table-info">
				<td><c:out value="${books.id}"></c:out></td>
				<td><a href="/books/${books.id}"><c:out value="${books.title}"></c:out></a></td>
				<td><c:out value="${books.language}"></c:out></td>
				<td><c:out value="${books.numberOfPages}"></c:out></td>
				<td><a href="/books/${books.id}/edit">Edit</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>	
</body>
</html>