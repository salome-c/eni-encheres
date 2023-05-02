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
	<main class="w-25 mx-auto mt-3">
		<section class="d-flex justify-content-between">
			<p>Pseudo :</p>
			<p>${profil.pseudo}</p>
		</section>
		<section class="d-flex justify-content-between">
			<p>Nom :</p>
			<p>${profil.nom}</p>
		</section>
		<section class="d-flex justify-content-between">
			<p>Prénom :</p>
			<p>${profil.prenom}</p>
		</section>
		<section class="d-flex justify-content-between">
			<p>Email :</p>
			<p>${profil.email}</p>
		</section>
		<section class="d-flex justify-content-between">
			<p>Téléphone :</p>
			<p>${profil.telephone}</p>
		</section>
		<section class="d-flex justify-content-between">
			<p>Rue :</p>
			<p>${profil.rue}</p>
		</section>
		<section class="d-flex justify-content-between">
			<p>Code postal :</p>
			<p>${profil.codePostal}</p>
		</section>
		<section class="d-flex justify-content-between">
			<p>Ville :</p>
			<p>${profil.ville}</p>
		</section>
		<c:if test="${profil.pseudo == utilisateur.pseudo}">
			<section class="d-flex justify-content-center">
				<a href="${pageContext.request.contextPath}/modifier-profil"><button class="btn btn-lg">Modifier</button></a>
			</section>
		</c:if>
	</main>
</body>
</html>