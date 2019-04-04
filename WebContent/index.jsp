<%@page import="modelo.Facultad"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Modelo"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<title>AJAX con GSON, JSON y JDBC</title>
</head>
<body>
	<h1>Alimentos a ingerir segun el momento y día</h1>
	<form id="frm1" action="AJAXMainController" method="get">
		<label for="facultades">¿Que comida es?:</label>
		<select id="facultades">
			<option></option>

			<jsp:useBean id="m" class="modelo.Modelo"></jsp:useBean>
			<c:forEach items="${m.facultades}" var="f">
				<option value='<c:out value="${f.id}"></c:out>'>
					<c:out value="${f.nombre}"></c:out>
				</option>
			</c:forEach>			
		</select>
		<label for="nombre">Nombre: </label>
		<input type="text" name="nombre" id="nombre">
		<label for="cantidad">Cantidad a ingerir: </label>
		<input type="text" name="cantidad" id="cantidad">
		<label for="descripcion">Descripcion alimento: </label>
		<input type="text" name="descripcion" id="descripcion">
		<label for="fechaAlta">Día a ingerirlo: </label>
		<input type="date" name="fechaAlta" id="fechaAlta">
		<input type="submit" value="Alta Estudiante">
		<output id="altaOK"></output>
	</form>
	<h3 id="t1"></h3>
	<div id="tabla">

	</div>
</body>

<script>
	document.getElementById("frm1")
				.addEventListener("submit", function(){
		// Impedimos que el evento submit tenga lugar
		event.preventDefault();
		event.stopPropagation();
		
		var objEstudiante = new Object();
		objEstudiante.id = 0;
		objEstudiante.idFacultad = document.getElementById("facultades")
			.options[document.getElementById("facultades").selectedIndex].value;
		objEstudiante.nombre = document.getElementById("nombre").value;
		objEstudiante.fechaAlta = document.getElementById("fechaAlta").value;
		objEstudiante.descripcion = document.getElementById("descripcion").value;
		objEstudiante.cantidad = document.getElementById("cantidad").value;
		
		var stringJSONEstudiante = JSON.stringify(objEstudiante);
		
		var xhttp = new XMLHttpRequest();
		
		xhttp.onreadystatechange = function() {
			if (this.readyState === 4 && this.status === 200) {
				var respuestaRecibida = this.responseText;
				document.getElementById("altaOK").innerHTML = respuestaRecibida;
				
			}
		};
		xhttp.open("POST", "AJAXMainController", true);
		xhttp.setRequestHeader("Content-type",
							   "application/x-www-form-urlencoded");
		xhttp.send("estudianteAltaJSON="+stringJSONEstudiante);
		
	});

	document.getElementById("facultades")
		.addEventListener("change", function(){
		// Impedimos que el evento submit tenga lugar
/* 		event.preventDefault();
		event.stopPropagation(); */
		
		var idFacultad = document.getElementById("facultades")
			.options[document.getElementById("facultades").selectedIndex].value;
		
		 var stringT1 = "Estudiantes Matriculados en la Facultad de ";
		 stringT1 = stringT1 + document.getElementById("facultades")
			.options[document.getElementById("facultades").selectedIndex].text;
		 
		 document.getElementById("t1").innerHTML = stringT1;
		
		var xhttp = new XMLHttpRequest();
		
		xhttp.onreadystatechange = function() {
			if (this.readyState === 4 && this.status === 200) {
				var respuestaRecibida = this.responseText;
				console.log(respuestaRecibida);
				var objRespuestaRecibida = JSON.parse(respuestaRecibida);
				console.log(objRespuestaRecibida);
				var tabla = "<table class='table table-hover'>";
				tabla = tabla + "<th scope='col'>Nombre</th>";
				tabla = tabla + "<th scope='col'>Descripción alimento</th>";
				tabla = tabla + "<th scope='col'>Cantidad a ingerir</th>";
		 	    tabla = tabla + "<th scope='col'>Fecha Alta</th>";
		 	    tabla = tabla + "</tr></thead><tbody>";
 				for(i=0;i<objRespuestaRecibida.length;i++ ) {		
 					tabla = tabla + "<tr>";
	 					tabla = tabla + "<td>" + objRespuestaRecibida[i].nombre +"</td>"
	 					tabla = tabla + "<td>" + objRespuestaRecibida[i].descripcion +"</td>"
	 					tabla = tabla + "<td>" + objRespuestaRecibida[i].cantidad + "gr."+"</td>"
	 					tabla = tabla + "<td>" + objRespuestaRecibida[i].fechaAlta +"</td>"
 					tabla = tabla + "</tr>";
 				}					 	  
				tabla = tabla +	"</tbody></table>";
				document.getElementById("tabla").innerHTML = tabla;
			}
		};
		xhttp.open("POST", "AJAXMostrarEstudiantes", true);
		xhttp.setRequestHeader("Content-type",
						   "application/x-www-form-urlencoded");
		xhttp.send("dato="+idFacultad);
		
	});
	
</script>

</html>