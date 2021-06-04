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
						class="nav-link py-3 px-0 px-lg-3 rounded" href="/Elecciones/Index">Index</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded"
						href="/Elecciones/RegistrarVotante">Registro</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<section class="page-section portfolio" id="portfolio mt-5">
		<div class="container">
			<!-- Portfolio Section Heading-->
			<h2
				class="page-section-heading mt-5 pt-5 text-center text-uppercase text-Dark mb-0">Elecciones</h2>
			<!-- Icon Divider-->
			<div class="divider-custom">
				<div class="divider-custom-line"></div>
				<div class="divider-custom-icon">
					<i class="fas fa-star"></i>
				</div>
				<div class="divider-custom-line"></div>
			</div>
			<div class="row row-cols-1 row-cols-sm-2 row-cols-lg-3 justify-content-center">
				<!-- Portfolio Item 1--> 
				<c:forEach var="eleccion" items="${elecciones}">
					<div class="col mb-5 text-center">
						<h3>${eleccion.nombre}</h3>
						<div class="portfolio-item mx-auto" data-bs-toggle="modal"
							data-bs-target="#portfolioModal${eleccion.id}">
							<div
								class="portfolio-item-caption d-flex align-items-center justify-content-center h-100 w-100">
								<div
									class="portfolio-item-caption-content text-center text-white">
									<i class="fas fa-search fa-3x"></i>
								</div>
							</div>
							<img class="img-fluid"
								src="<c:url value ="/img/Elecciones.jpg"/>" alt="..." />
						</div>
						<div class="row mx-3">
							<a class="btn btn-danger rounded-0 col w-100" href="/Elecciones/Admin/eliminarEleccion?id=${eleccion.id}" role="button">Eliminiar Eleccion</a>
							<a class="btn btn-info rounded-0 col w-100" href="/Elecciones/Admin/editarEleccion?id=${eleccion.id}" role="button">Editar Eleccion</a>
						</div>	
					</div>
				</c:forEach>
			<div class="col mb-5 text-center">
				<h3>Crear Nueva Eleccion</h3>
				<div class="portfolio-item mx-auto" data-bs-toggle="modal"
					data-bs-target="#portfolioModalDef">
					<div
						class="portfolio-item-caption d-flex align-items-center justify-content-center h-100 w-100">
						<div class="portfolio-item-caption-content text-center text-white">
							<i class="fas fa-plus fa-3x"></i>
						</div>
					</div>
					<img class="img-fluid" src="<c:url value ="/img/Elecciones.jpg"/>"
						alt="..." />
				</div>
			</div>
		</div>
		</div>
	</section>
	
	<section class="page-section bg-dark text-white mb-0" id="about">
            <div class="container">
                <!-- About Section Heading-->
                <h2 class="page-section-heading text-center text-uppercase text-white">Configuracion</h2>
                <!-- Icon Divider-->
                <div class="divider-custom divider-light">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon"><i class="fas fa-cog"></i></div>
                    <div class="divider-custom-line"></div>
                </div>
                <!-- About Section Content-->
                <div class="row">
            <a href="${pageContext.request.contextPath}/Voto.jsp" class="btn btn-xl btn-outline-light">Tipos De Documentos</a>        
			<a href="${pageContext.request.contextPath}/Tipodocumento.jsp" class="btn btn-xl btn-outline-light">Tipos De Documentos</a>
			<a href="${pageContext.request.contextPath}/RegistrarEstamento.jsp" class="btn btn-xl btn-outline-light">Estamento</a> </div>
                <!-- About Section Button-->
                <div class="text-center mt-4">
                </div>
            </div>
        </section>
	
	
	<!-- Footer-->
	<footer class="footer text-center">
		<div class="container">
			<div class="row">
				<!-- Footer Location-->
				<div class="col-lg-4 mb-5 mb-lg-0">
					<h4 class="text-uppercase mb-4">Location</h4>
					<p class="lead mb-0">
						2215 John Daniel Drive <br /> Clark, MO 65243
					</p>
				</div>
				<!-- Footer Social Icons-->
				<div class="col-lg-4 mb-5 mb-lg-0">
					<h4 class="text-uppercase mb-4">Around the Web</h4>
					<a class="btn btn-outline-light btn-social mx-1" href="#!"><i
						class="fab fa-fw fa-facebook-f"></i></a> <a
						class="btn btn-outline-light btn-social mx-1" href="#!"><i
						class="fab fa-fw fa-twitter"></i></a> <a
						class="btn btn-outline-light btn-social mx-1" href="#!"><i
						class="fab fa-fw fa-linkedin-in"></i></a> <a
						class="btn btn-outline-light btn-social mx-1" href="#!"><i
						class="fab fa-fw fa-dribbble"></i></a>
				</div>
				<!-- Footer About Text-->
				<div class="col-lg-4">
					<h4 class="text-uppercase mb-4">About Freelancer</h4>
					<p class="lead mb-0">
						Freelance is a free to use, MIT licensed Bootstrap theme created
						by <a href="/Elecciones/Admin">Admin</a>  .
					</p>
				</div>
			</div>
		</div>
	</footer>
	<!-- Copyright Section-->
	<div class="copyright py-4 text-center text-white">
		<div class="container">
			<small>Copyright &copy; Your Website 2021</small>
		</div>
	</div>
	<div class="portfolio-modal modal fade" id="portfolioModalDef"
		tabindex="-1" aria-labelledby="portfolioModalDef" aria-hidden="true">
		<div class="modal-dialog modal-xl">
			<div class="modal-content">
				<div class="modal-header border-0">
					<button class="btn-close" type="button" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body text-center pb-5">
					<div class="container">
						<div class="row justify-content-center">
							<div class="col-lg-8">
								<!-- Portfolio Modal - Title-->
								<h2
									class="portfolio-modal-title text-secondary text-uppercase mb-0">Crear
									Eleccion</h2>
								<!-- Icon Divider-->
								<div class="divider-custom">
									<div class="divider-custom-line"></div>
									<div class="divider-custom-icon">
										<i class="fas fa-star"></i>
									</div>
									<div class="divider-custom-line"></div>
								</div>
								<form class="justify-content-center"
									action="/Elecciones/Admin/insert" method="POST"
									class="w-50">
									<fieldset>
										<div class="mb-3">
											<label for="nombre" class="form-label">Nombre </label> <input
												name="nombre" type="text" id="nombreText"
												class="form-control" placeholder="Nombre">
										</div>

										<div class="mb-3">
											<label for="fechainicio" class="form-label">Fecha de
												Inicio </label> <input name="fechainicio" type="date"
												id="fechaBegin" class="form-control">
										</div>
										<div class="mb-3">
											<label for="fechafin" class="form-label">Fecha de
												Terminacion </label> <input name="fechafin" type="date"
												id="fechaend" class="form-control">
										</div>

										<div class="mb-3">
											<label for="cargo" class="form-label">Cargo </label> <input
												name="cargo" type="text" id="cargoInput"
												class="form-control" placeholder="Cargo">
										</div>
										<button type="submit" class="btn btn-primary">Registrar
											Eleccion</button>
									</fieldset>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<c:forEach var="eleccion" items="${elecciones}">
		
		<div class="portfolio-modal modal fade"
			id="portfolioModal${eleccion.id}" tabindex="-1"
			aria-labelledby="portfolioModal${eleccion.id}" aria-hidden="true">
			<div class="modal-dialog modal-xl">
				<div class="modal-content">
					<div class="modal-header border-0">
						<button class="btn-close" type="button" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body text-center pb-5">
						<div class="container">
							<div class="row justify-content-center">
								<div class="col-lg-8">
									<!-- Portfolio Modal - Title-->
									<h2
										class="portfolio-modal-title text-secondary text-uppercase mb-0">Candidatos</h2>
										
										<a href="Admin/lista?id=${eleccion.id}">Votantes Inscritos</a>
										
									<!-- Icon Divider-->
									<div class="divider-custom">
										<div class="divider-custom-line"></div>
										<div class="divider-custom-icon">
											<i class="fas fa-star"></i>
										</div>
										<div class="divider-custom-line"></div>
									</div>
									<div class="row row-cols-2 justify-content-center">
										<!-- Portfolio Item 1-->
										<c:forEach var="candidato" items="${candidatos}">
											<c:if test = "${eleccion.id == candidato.eleccion.id}">
											<div class="col mb-5 text-center">
												<h3>${candidato.nombre}</h3>
												<div class="portfolio-item mx-auto" data-bs-toggle="modal"
													data-bs-target="#portfolioModal${candidato.id}">
													<div
														class="portfolio-item-caption d-flex align-items-center justify-content-center h-100 w-100">
														<div
															class="portfolio-item-caption-content text-center text-white">
															<i class="fas fa-plus fa-3x"></i>
														</div>
													</div>
													<img class="img-fluid"
														src="<c:url value ="/img/Elecciones.jpg"/>" alt="..." />
														<a class="btn btn-danger" href="/Elecciones/Admin/eliminarCandidato?id=${candidato.id}" role="button">Eliminiar Candidato</a>
														<a class="btn btn-info" href="/Elecciones/Admin/editarCandidato?id=${candidato.id}" role="button">Editar Candidato</a>
												</div>
											</div>
											</c:if>
										</c:forEach>
										<p>
											<a class="btn btn-success" data-bs-toggle="collapse"
												href="#multiCollapseExample1" role="button"
												aria-expanded="false" aria-controls="multiCollapseExample1">Agregar Candidato</a>
												
										</p>
										<div class="container">
											<div class="">
												<div class="collapse multi-collapse"
													id="multiCollapseExample1">
													<div class="card card-body"><form class="justify-content-center"
														action="/Elecciones/Admin/insertCandidato" method="POST"
														class="w-100">
														<fieldset>
															<div class="mb-3">
																<label for="nombre" class="form-label">Nombre </label> <input
																	name="nombre" type="text" id="nombreText"
																	class="form-control" placeholder="Nombre">
															</div>
															<div class="mb-3">
																<label for="apellido" class="form-label">Apellido </label> <input
																	name="apellido" type="text" id="apellidoInput"
																	class="form-control" placeholder="Cargo">
															</div>
															<div class="mb-3">
																<label for="documento" class="form-label">Documento </label> <input
																	name="documento" type="text" id="documentoInput"
																	class="form-control" placeholder="Cargo">
															</div>
															<div class="mb-3">
																<label for="eleccion" class="form-label">Eleccion </label> <select
																	name="eleccion" id="eleccion"
																	class="form-control">
																	<c:forEach var="e" items="${elecciones}">
																		<option value="${e.id}">${e.nombre}</option>
																	</c:forEach>
																	</select>
															</div>
															<div class="mb-3">
																<label for="numero" class="form-label">Numero </label> <input
																	name="numero" type="number" id="numeroInput"
																	class="form-control">
															</div>
															<button type="submit" class="btn btn-primary">Registrar
																Candidato</button>
														</fieldset>
													</form>
												</div>
											</div>
										</div>
									</div>
									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
		
	</c:forEach>
	
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
</body>
</html>