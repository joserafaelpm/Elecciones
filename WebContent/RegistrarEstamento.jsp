<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.15.3/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link rel="stylesheet" href="<c:url value ="/CSS/Estilos.css"/>" />
<title>Registrar Estamento</title>
</head>
<body>
	<jsp:useBean class="ufps.edu.co.dao.EstamentoDao" id="estamentoDAO"></jsp:useBean>
	<jsp:useBean class="ufps.edu.co.dao.EleccionesDao" id="eleccionDAO"></jsp:useBean>
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg bg-danger text-uppercase fixed-top"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand" href="/Elecciones/Admin">Elecciones Estudiantiles</a>
			<button
				class="navbar-toggler text-uppercase font-weight-bold bg-white text-black rounded"
				type="button" data-bs-toggle="collapse"
				data-bs-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded"
						href="/Elecciones/Index">Index</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded"
						href="/Elecciones/RegistrarVotante">Registro</a></li>
				</ul>
			</div>
		</div>
	</nav>	
	
<div class="container pt-3">
	<h1 class="mt-5 pt-5">
		<b>Registrar Estamento</b>
	</h1>
	<c:if test="${estamento != null}">
		<%--  <form action="${pageContext.request.contextPath}/ActualizarEmpleado?accion=edita&codigo=${empleado.codigo}"  method="post"> --%>
		<form action="EstamentoServlet?action=actualizar&id=${estamento.id}"
			method="POST">
	</c:if>
	<c:if test="${estamento == null}">
		<%--  <form action="${pageContext.request.contextPath}/AgregarEmpleado?accion=agregar&codigo=${empleado.codigo}"  method="POST"> --%>
		<form action="EstamentoServlet?action=registrar" method="POST">
	</c:if>
	<div class="form-group row">
		<div class="col-sm-10">
			<label for="inputEmail3" class="col-sm-2 col-form-label">Descripcion:</label>
			<input type="text" name="descripcion"
				value="${estamento.descripcion}" class="form-control"
				id="inputEmail3">
		</div>
	</div>


	<fieldset class="form-group">
		<label for="eleccion" class="form-label">Eleccion:</label> <select
			class="form-control" id="exampleFormControlSelect1" name="eleccion">
			<c:forEach items="${eleccionDAO.list()}" var="eleccion">
				<option value="${eleccion.id}">${eleccion.nombre}</option>
			</c:forEach>
		</select>
	</fieldset>

	<div class="form-group row">
		<div class="col-sm-10">
			<button type="submit" class="btn btn-primary">Registrar</button>
			<a href="${pageContext.request.contextPath}/index.jsp"
				class="btn btn-primary">Volver</a>
		</div>
	</div>

	<!--Esta parte esta la tabla de estamento  -->

	<h1>
		<b>Estamentos</b>
	</h1>
	<table class="table">
		<thead>
			<tr>
				<th scope="col">Id</th>
				<th scope="col">Descripcion</th>
				<th scope="col">Eleccion</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${estamentoDAO.list()}" var="estamento">
				<tr>
					<th scope="row"><c:out value="${estamento.id}" /></th>
					<td><c:out value="${estamento.descripcion}" /></td>
					<td><c:out value="${estamento.eleccion.getNombre()}" /></td>
					<td><a
						href="EstamentoServlet?action=eliminar&id=${estamento.id}">Eliminar</a></td>
					<td><a
						href="EstamentoServlet?action=mostrar&id=${estamento.id}">Editar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>	

<!-- Footer-->
	<div class="footerContainer"></div>
	
	<!-- Bootstrap core JS-->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="<c:url value="/Js/Scripts.js"/>"></script>		
</body>
</html>