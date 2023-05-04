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
		<h2 class="text-center my-3">Détail vente</h2>

		<p>${article.nomArticle}</p>
		
		<section class="d-flex">
			<p class="w-50">Description :</p>
			<p>${article.description}</p>
		</section>
		
		<section class="d-flex">
			<p class="w-50">Catégorie :</p>
			<p>${categorie.libelle}</p>
		</section>
		
		<p>Meilleure offre :</p>
		
		<section class="d-flex">
			<p class="w-50">Mise à prix :</p>
			<p>${article.miseAPrix}</p>
		</section>
		
		<section class="d-flex">
			<p class="w-50">Fin de l'enchère :</p>
			<p>${article.dateFinEncheres}</p>
		</section>
		
		<section class="d-flex">
			<p class="w-50">Retrait :</p>
			<section>
				<p>${retrait.rue}</p>
				<p>${retrait.codePostal} ${retrait.ville}</p>
			</section>
		</section>
		
		<section class="d-flex">
			<p class="w-50">Vendeur :</p>
			<p>${vendeur.pseudo}</p>
		</section>
		
		<form class="d-flex align-items-center">
			<label for="proposition" class="w-50">Ma proposition :</label>
			<input type="number" min="${article.miseAPrix + 1}" value="${article.miseAPrix + 10}" class="w-25">
			<input type="submit" value="Enchérir" disabled class="btn btn-lg">
		</form>
	</main>
</body>
</html>
