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
			<h1>Administración de Usuarios</h1><hr/>
			<table class="table table-striped table-hover">
			  <thead class="thead-light">
			    <tr>
			      <th scope="col" style="width:100%;">Usuario</th>
			      <th scope="col"></th>
			    </tr>
			  </thead>
			  <tbody>
				<c:forEach items="${users}" var="user">
			    <tr>
			      <td style="width:100%;">${user}</td>
			      <td>
			      	<form action="/delete-user" method="post">
			      		<input type="hidden" id="username" name="username" value='${user}'>
			      		<button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
			      	</form>
		      	  </td>
			    </tr>
				</c:forEach>
			  </tbody>
			</table>
	      	<form action="/logout" method="post">
	      		<button type="submit" class="btn btn-info">Logout</button>
	      	</form>
		</div>
	</div>
	
</body>

</html>
