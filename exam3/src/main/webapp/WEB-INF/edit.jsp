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
    <title>EditA Song</title>
    
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div>
		<h1>Add to <c:out value="${song.title}"></c:out></h1>
		<div>
			<form:form action="/song/${song.id}" modelAttribute="song" class="form" method="post">
			<input type="hidden" name="_method" value="put"/>
				<div class="form-row">
				 	<form:errors path="title" class="error"/>
					<form:label for="title" path="title">Song Title:</form:label>
					<form:input type="text" path="title" class="form-control"/>
				</div>
				
				<div class="form-row">
					<form:errors path="genre" class="error"/>
					<form:label for="genre" path="genre">Genre:</form:label>
					<form:input type="text" path="genre" class="form-control"/>
				</div>
				
				<div class="form-row">
					<form:errors path="lyrics" class="error"/>
					<form:label for="lyrics" path="lyrics">Add your lyrics:</form:label>
					<form:textarea path="lyrics" class="form-control"/>
				</div>
				
				<div class="form-row">
					<form:errors path="user" class="error"/>
					<form:input type="hidden" path="user" value="${user.id}" class="form-control"/>
				</div>
				<div class="form-row">
					<form:input type="hidden" path="user" value="${song.id}" class="form-control"/>
				</div>
				
				<div class="form-row">
					<input type="submit" value="Submit" class="btn-primary"/>
				</div>
				
			</form:form>
		</div>
		<div class="d-flex flex-row bd-highlight">
			<a href="/home">Cancel</a>
			<form action="/songs/${song.id}/delete" method="POST">
				<input type="hidden" name="_method" value="DELETE"/>
				<input type="submit" value="Delete" class="btn btn-danger"/>
			</form>	
		</div>
	</div>
</body>
</html>