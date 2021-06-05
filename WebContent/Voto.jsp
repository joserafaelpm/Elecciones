<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Votos</title>
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
<jsp:useBean class="ufps.edu.co.dao.VotoDao" id="votoDAO"></jsp:useBean>
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

	<!--Esta parte esta la tabla  -->
	<div class="container pt-5 mt-5 mb-5">
		<h1 class="pt-5">
			<b>Votos Registrados</b>
		</h1>
	<table class="table">
		<thead>
			<tr>
				<th scope="col">Id</th>
				<th scope="col">Enlace</th>
				<th scope="col">Fecha de creacion</th>
				<th scope="col">Fecha de voto</th>
				<th scope="col">uuid</th>
				<th scope="col">Candidato</th>
				<th scope="col">Estamento</th>
				<th scope="col">Votante</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${votoDAO.list()}" var="voto">
				<tr>
					<th scope="row"><c:out value="${voto.id}" /></th>
					<td><c:out value="${voto.enlace}" /></td>
					<td><c:out value="${voto.fechaCreacion}" /></td>
					<td><c:out value="${voto.fechaVoto}" /></td>
					<td><c:out value="${voto.uuid}" /></td>
					<td><c:out value="${voto.candidato.getApellido()}" /></td>
					<td><c:out value="${voto.estamento.getDescripcion()}" /></td>
					<td><c:out value="${voto.votante.getDocumento()}" /></td>
					<td><a
						href="VotoServlet?action=eliminar&id=${voto.id}">Eliminar</a></td>
					<td><a
						href="VotoServlet?action=mostrar&id=${voto.id}">Editar</a></td>
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