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
    <title>Omikuji</title>
    
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="wrapper">
   <h1>Send an Omikuji!</h1>
	
	<form action="/formData" method="post" class="border border-primary w-25 p-3 p-3 mb-2 bg-secondary text-white">
		<div>
			<label for="">Pick any number from 5 to 25</label><br />
			<input type="number" name="years"/>
		</div>
		<div>
			<label for="">Enter the name of any city</label><br />
			<input type="text" name="city"/>
		</div>
		<div>
			<label for="">Enter the name of any real person</label><br />
			<input type="text" name="person"/>
		</div>
		<div>
			<label for="">Enter professional endeavor or hobby</label><br />
			<input type="text" name="hobby"/>
		</div>
		<div>
			<label for="">Enter any type of living thing</label><br/>
			<input type="text" name="animal"/>
		</div>
		<div>
			<label for="">Say something nice to someone:</label><br />
			<textarea name="message" id="" cols="20" rows="5"></textarea>
		</div>
		<p>Send and show a friend!</p>
		<input class="btn btn-primary" type="submit" value="Send"/>
	</form>
</div>
</body>
</html>