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
	<h1>Edit Expense<c:out value="${travelService.expense}"></c:out></h1>	
	<div>
	<form:form action="/expenses/${travelService.id}/edit" method="PUT" modelAttribute="travelService" class="border border-primary w-25 p-3 p-3 mb-2 bg-secondary text-white">
			<input type="hidden" name="_method" value="PUT"/>
			<div>
				<form:label path="expense">Expense</form:label><br/>
				<form:errors path="expense" class="text-danger"/>
				<form:input type="text" path="expense"/>
			</div>
			<div>
				<form:label path="vendor">Vendor</form:label><br/>
				<form:errors path="vendor" class="text-danger"/>
				<form:input type="text" path="vendor"/>
			</div>
			<div>
				<form:label path="amount">Amount</form:label><br/>
				<form:errors path="amount" class="text-danger"/>
				<form:input type="number" path="amount"/>
			</div>
			<div>
				<form:label path="description">Description</form:label><br/>
				<form:errors path="description" class="text-danger"/>
				<form:input type="text" path="description"/>
			</div>
			<input class="btn btn-primary" type="submit" value="Submit"/>
	</form:form>
	</div>	
</body>
</html>