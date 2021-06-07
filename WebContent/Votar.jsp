<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>

<script src="https://www.google.com/recaptcha/api.js" async defer></script>
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
<script type="text/javascript">
	function continuar() {
		var response = grecaptcha.getResponse();
		if (response.length != 0)
			windows.open("");
		else
			document.getElementById('status').innerHTML = "Acepta el Captcha primero.";
		windows.open("");
	}
</script>
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
				</ul>
			</div>
		</div>
	</nav>
	<!-- Cuerpo -->
	<header class="masthead bg-light text-black text-center">
		<div class="container d-flex align-items-center flex-column">
			<h1 class="masthead-heading text-uppercase mb-0">${voto.getEstamento().getEleccion().getNombre()}</h1>
			<!-- Icon Divider-->
			<div class="divider-custom">
				<div class="divider-custom-line"></div>
				<div class="divider-custom-icon">
					<i class="fas fa-star"></i>
				</div>
				<div class="divider-custom-line"></div>
			</div>


			<form action="Voto/votar/" method="POST">
				<input type="hidden" name="id" value="${voto.id}" />
				<div
					class="row row-cols-1 row-cols-sm-2 row-cols-lg-3 justify-content-center">
					<c:forEach var="candidato" items="${candidatos}">
						<div class="form-check">
							<input class="form-check-input" value="${candidato.id}"
								type="radio" name="candidatoId" id="candidatoId"> <label
								class="form-check-label" for="flexRadioDefault1">
								<h3>${candidato.nombre} Numero: ${candidato.numero}</h3>
							</label>
							<div class="col mb-5 text-center">

								<div class="portfolio-item mx-auto" data-bs-toggle="modal"
									data-bs-target="#portfolioModal${candidato.id}">
									<div
										class="portfolio-item-caption d-flex align-items-center justify-content-center h-100 w-100">
										<div
											class="portfolio-item-caption-content text-center text-white">
											<i class="fas fa-plus fa-3x"></i>
										</div>
									</div>
									<a href="hola"> <img class="img-fluid"
										src="<c:url value ="/img/Elecciones.jpg"/>" alt="..."></img>
									</a>
								</div>
							</div>

						</div>
					</c:forEach>
				</div>
				<div class="g-recaptcha"
					data-sitekey="6LdNURgbAAAAAPErZBouNZj-ijZpwojxvBHS8Iym"></div>
				<br />
				<div id="status"></div>
				<button type="submit" onclick="continuar()" class="btn btn-primary">Votar</button>
			</form>
		</div>

	</header>
	<!-- Footer -->
	<div class="footerContainer"></div>

	<!-- Bootstrap core JS-->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="<c:url value="/Js/Scripts.js"/>"></script>
</body>
</html>