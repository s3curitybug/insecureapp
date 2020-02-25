<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>

<head>
	<meta charset="UTF-8">
	<title>Admin</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>

<body>

	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	
	<div>
		<div class="container" style="margin-top: 5em;">
			<h1>¡Hola!</h1><hr/>
			<h3>Descárgate la guía de uso: <a href="/download?filename=guide.txt" download="guide.txt">Guía</a></h3>
	      	<form action="/logout" method="post" style="margin-top: 2em;">
	      		<button type="submit" class="btn btn-info">Logout</button>
	      	</form>
		</div>
	</div>
	
</body>

</html>
