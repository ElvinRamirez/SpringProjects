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
    <title>Save Travels</title>
    
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<h1>Save Travels</h1>
	<div>
		<table class="table">
			<thead>
				<tr class="table-dark">
					<th>Expense</th>
					<th>Vendor</th>
					<th>Amount</th>
					<th>Description</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<tr class="table-info">
					<td><a href=""><c:out value="${travelService.expense}"></c:out></a></td>
					<td><c:out value="${travelService.vendor}"></c:out></td>
					<td><c:out value="${travelService.amount}"></c:out></td>
					<td><c:out value="${travelService.description}"></c:out></td>
					<td><a href="/expenses/${travelService.id}/edit" class="btn btn-warning">Edit</a>
					<form action="/expenses/${travelService.id}/delete" method="POST">
						<input type="hidden" name="_method" value="DELETE"/>
						<input type="submit" value="Delete" class="btn btn-danger"/>
					</form>
					</td>
				</tr>
			</tbody>
		</table>
	</div>	
</body>
</html>