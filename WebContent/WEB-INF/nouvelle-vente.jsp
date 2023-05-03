<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ENI-Enchères</title>
	<link href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container-fluid">
	<header class="d-flex justify-content-between align-items-center">
		<h1 class="m-0">ENI-Enchères</h1>
	</header>
	<main class="w-25 mx-auto">
		<h2 class="text-center my-3">Nouvelle vente</h2>
		<form action="${pageContext.request.contextPath}/nouvelle-vente" method="post" class="mt-3 mb-3">
			<label for="nom" class="w-25">Article* :</label>
			<input type="text" name="nom" required><br>
			
			<label for="description">Description* :</label>
			<input type="text" name="description" required><br>
			
			<label for="categorie" class="w-50">Catégorie* :</label>
			<select name="categorie" required>
				<c:forEach var="categorie" items="${categories}">
					<option value="${categorie.noCategorie}">${categorie.libelle}</option>
				</c:forEach>
			</select><br>
			
			<label for="photo">Photo de l'article :</label>
			<input type="file" name="photo" disabled><br>
			
			<label for="prix" class="w-50">Mise à prix* :</label>
			<input min="0" type="number" name="prix" id="prix" required class="w-25" oninput="checkPrix()"><br>
			
			<label for="dateDebutEnchere" class="w-50">Début de l'enchère* :</label>
			<input type="date" name="dateDebutEnchere" id="dateDebutEnchere" oninput="checkDates()" required><br>
			
			<label for="dateFinEnchere" class="w-50">Fin de l'enchère* :</label>
			<input type="date" name="dateFinEnchere" id="dateFinEnchere" oninput="checkDates()" required><br>
			
			<section id="prix-check" class="w-100 text-center"></section>
			
			<section id="dates-check" class="w-100 text-center"></section>
			
			<fieldset class="border border-primary p-2 mb-3">
				<legend>Retrait</legend>
				<label for="rue" class="w-50">Rue* :</label>
				<input type="text" name="rue" value="${utilisateur.rue}" required class="w-25"><br>
				
				<label for="codePostal" class="w-50">Code postal* :</label>
				<input type="text" name="codePostal" value="${utilisateur.codePostal}" required pattern="[0-9]{5}" class="w-25"><br>
				
				<label for="ville" class="w-50">Ville* :</label>
				<input type="text" name="ville" value="${utilisateur.ville}" required class="w-25"><br>
			</fieldset>
			
			<c:if test="${not empty requestScope.erreurCreationVenteOuRetrait}">
			  <section class="w-100 text-center">${requestScope.erreurCreationVenteOuRetrait}</section>
			</c:if>
			
			<section class="w-100 d-flex justify-content-around">
				<input type="submit" value="Enregistrer" class="btn btn-lg">
				<a href="${pageContext.request.contextPath}/liste-encheres"><input type="button" value="Annuler" class="btn btn-lg"></a>
			</section>
		</form>
	</main>
	<script>
		function checkDates() {
		  var dateDebutEnchere = document.getElementById("dateDebutEnchere");
		  var dateFinEnchere = document.getElementById("dateFinEnchere");
		  var datesCheck = document.getElementById("dates-check");
		  
		  if (dateFinEnchere.value < dateDebutEnchere.value) {
			  datesCheck.innerHTML = "La date de fin d'enchère ne peut pas être antérieure à la date de début d'enchère";
			  dateFinEnchere.setCustomValidity("La date de fin d'enchère ne peut pas être antérieure à la date de début d'enchère");
		  } else {
			  datesCheck.innerHTML = "";
			  dateFinEnchere.setCustomValidity("");
		  }
		}
	</script>
</body>
</html>
