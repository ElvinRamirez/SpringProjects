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
	<div>
		<div>
			<h1>Welcome <c:out value="${user.name}"></c:out></h1>
		</div>
		<div>
			<h2>Books from everyone's shelves:</h2>
		</div>
		<div>
			<table class="table">
				<tr class="table-dark">
					<th>ID</th>
					<th>Title</th>
					<th>Author Name</th>
					<th>Posted By</th>
				</tr>
				<c:forEach var="eachBook" items="${books}">
					<tr class="table-info">
						<td><c:out value="${eachBook.id}"/></td>
						<td><a href="books/${eachBook.id}"><c:out value="${eachBook.title}"/></a></td>
						<td><c:out value="${eachBook.author}"/></td>
						<td><c:out value="${eachBook.user.name}"/></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	<a href="/createForm">+ Add to my shelf</a>
	<a href="/logout">Logout</a>
	</div>
</body>
</html>