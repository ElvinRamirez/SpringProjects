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
    <title>Exam</title>
    
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
		<div>
			<h1>Hello, <c:out value="${user.name}"></c:out></h1>
			<a href="/logout">Logout</a>
		</div>
		<div>
			<h2>All Song Labs</h2>
		</div>
		<div class="d-flex flex-row bd-highlight justify-content-around">
			<div>
				<h3>Song</h3>
			</div>
			<div>
				<h3># of Collaborations</h3>
			</div>
		</div>
		<hr>
		<div>
			<c:forEach var="eachsong" items="${songs}">
					<div class="d-flex flex-row bd-highlight justify-content-around">
						<div>	
							<h4><a href="/songs/${eachsong.id}"><c:out value="${eachsong.title}"></c:out></a></h4>
							<h5>Genre: <c:out value="${eachsong.genre}"></c:out></h5>
						</div>
						<div>
							<h4 >Counter: <c:out value="${eachsong.timesEdited}"></c:out></h4>
						</div>
					</div>
			</c:forEach>
		</div>
		<a href="/songs/new">New Song</a>
</body>
</html>