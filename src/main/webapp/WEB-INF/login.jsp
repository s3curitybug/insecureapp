<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>

<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>

<body>

	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	
	<div>
		<div class="container" style="margin-top: 5em;">
			<h1>Login</h1><hr/>
			<form action="/login" method="post">
			  <div class="form-group">
				<label for="username">Usuario</label>
			    <input type="text" class="form-control" id="username" name="username" autofocus required>
			  </div>
			  <div class="form-group">
			    <label for="password">Contraseña</label>
			    <input type="password" class="form-control" id="password" name="password" required>
			  </div>
			  <button type="submit" class="btn btn-primary">Login</button>
			  <c:if test="${invalidCredentials}">
				<div class="alert alert-warning" role="alert" style="margin-top: 1em;">Credenciales Incorrectos</div>
			  </c:if>
			</form>
			
			<h1 style="margin-top: 2em;">Registro</h1><hr/>
			<form action="/register" method="post">
			  <div class="form-group">
				<label for="username">Usuario</label>
			    <input type="text" class="form-control" id="username" name="username" required>
			  </div>
			  <div class="form-group">
			    <label for="password">Contraseña</label>
			    <input type="password" class="form-control" id="password" name="password" required>
			  </div>
			  <button type="submit" class="btn btn-success">Registrar</button>
			  <c:if test="${alreadyRegistered}">
				<div class="alert alert-warning" role="alert" style="margin-top: 1em;">El Usuario Ya Existe</div>
			  </c:if>
			</form>
		</div>
	</div>
	
</body>

</html>
