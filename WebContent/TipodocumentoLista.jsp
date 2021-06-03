<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<title>Tipos de documento</title>
</head>
<body>
<jsp:useBean class="ufps.edu.co.dao.TipoDocumentoDao" id="tipoDocumentoDAO"></jsp:useBean>
<h1><b>Tipos de documento</b></h1>
<a href="${pageContext.request.contextPath}/TipodocumentoRegistro.jsp" class="btn btn-primary mb-3">Registrar Tipo de documento</a>
<a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-primary mb-3">Volver</a>
  	<table class="table">
  <thead>
    <tr>
    <th scope="col">Id</th>
    <th scope="col">Descripcion</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${tipoDocumentoDAO.list()}" var = "tipoDocumento" >
    <tr>
      <th scope="row"> <c:out value="${tipoDocumento.id}" /></th>
      <td><c:out value="${tipoDocumento.descripcion}" /></td>
      <td><a href="TipodocumentoServlet?action=eliminar&id=${tipoDocumento.id}">Eliminar</a></td>
      <td><a href="TipodocumentoServlet?action=mostrar&id=${tipoDocumento.id}">Editar</a></td>
    </tr>
 </c:forEach>
  </tbody>
</table>
</body>
</html>