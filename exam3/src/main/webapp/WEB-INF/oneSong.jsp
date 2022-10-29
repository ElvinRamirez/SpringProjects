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
    <title>Song</title>
    
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div>
		<h1><c:out value="${song.title}"/></h1>
	</div>
	<div>
		<c:forEach var="eachsong" items="${songs}">
			<h3>(Started by <c:out value="${eachsong.users.name}"/>)</h3>
		</c:forEach>
	</div>
	<div>
		<h4>Genre: <c:out value="${song.genre}"/></h4>
	</div>
	<div>
		<h4>Lyrics:</h4>
	</div>
	<div class="text-wrap" style="width: 14rem;">
		<p><c:out value="${song.lyrics}"/></p>
	</div>
	<div>
		<a href="/songs/${song.id}/edit" type="button" class="btn btn-secondary">Contribute</a>
	</div>
	<hr>
	<div>
		<h1>Collaborators</h1>
		<c:forEach var="eachsong" items="${songs}">
		<!-- Need to add updated by column to songs to show collabotators -->
			<span><c:out value=""></c:out></span>
		</c:forEach>
	</div>
</body>
</html>