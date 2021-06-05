<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SistemaDeVotacion</title>
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
</head>
<body>
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg bg-danger text-uppercase fixed-top"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand" href="#page-top">Elecciones Estudiantiles</a>
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

	<!--Esta parte esta la tabla  -->
	<div class="container pt-5 mt-5 mb-5">
		<h1 class="pt-5">
			<b>Votantes Registrados</b>
		</h1>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">Id</th>
					<th scope="col">Documento</th>
					<th scope="col">Email</th>
					<th scope="col">Nombre</th>
					<th scope="col">Eleccion</th>
					<th scope="col">Tipo de documento</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${votantes}" var="votante">
					<c:if test="${votante.eleccion.id == eleccion.id}">
					<tr>
						<th scope="row"><c:out value="${votante.id}" /></th>
						<td><c:out value="${votante.documento}" /></td>
						<td><c:out value="${votante.email}" /></td>
						<td><c:out value="${votante.nombre}" /></td>
						<td><c:out value="${votante.eleccion.getNombre()}" /></td>
						<td><c:out value="${votante.tipoDocumento.getDescripcion()}" /></td>
						<td><a
							href="/Elecciones/RegistrarVotante/delete?id=${votante.id}">Eliminar</a></td>
						<td><a
							href="/Elecciones/RegistrarVotante/edit?id=${votante.id}">Editar</a></td>
					</tr>
					</c:if>
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