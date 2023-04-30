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
		<c:if test="${not empty utilisateur}">
			<p class="m-0">${utilisateur.pseudo} est connecté</p>
		</c:if>
		<nav>
			<ul class="d-flex align-items-center m-0">
				<c:if test="${not empty utilisateur}">
					<li class="list-inline-item"><a href="#">Enchères</a></li>
					<li class="list-inline-item"><a href="#">Vendre un article</a></li>
					<li class="list-inline-item"><a href="#">Mon profil</a></li>
					<li class="list-inline-item"><a href="${pageContext.request.contextPath}/deconnexion">Déconnexion</a></li>
				</c:if>
				<c:if test="${empty utilisateur}">
					<li class="list-inline-item"><a href="${pageContext.request.contextPath}/connexion">S'inscrire - Se connecter</a></li>
				</c:if>
			</ul>
		</nav>
	</header>
	<main class="px-5">
		<h2 class="text-center my-3">Liste des enchères</h2>
		<form name="filtres" class="d-flex mb-3">
			<section class="d-flex flex-column w-50">
				<section class="w-75">
					<p>Filtres :</p>
					<input type="text" placeholder="Le nom de l'article contient" class="w-100">
					<section class="d-flex justify-content-between my-3">
						<label>Catégorie :</label>
						<select>
							<option selected>Toutes</option>
							<option>Catégorie 1</option>
							<option>Catégorie 2</option>
							<option>Catégorie 3</option>
						</select>
					</section>
				</section>
				<c:if test="${not empty utilisateur}">
					<section class="d-flex justify-content-between">
						<fieldset class="d-flex flex-column">
							<label><input type="radio" name="filtre">Achats</label>
							<label><input type="checkbox" name="achats">Enchères ouvertes</label>
							<label><input type="checkbox" name="achats">Mes enchères en cours</label>
							<label><input type="checkbox" name="achats">Mes enchères remportées</label>
						</fieldset>
						<fieldset class="d-flex flex-column">
							<label><input type="radio" name="filtre">Mes ventes</label>
							<label><input type="checkbox" name="ventes">Mes ventes en cours</label>
							<label><input type="checkbox" name="ventes">Ventes non débutées</label>
							<label><input type="checkbox" name="ventes">Ventes terminées</label>
						</fieldset>
					</section>
				</c:if>
			</section>
			<section class="d-flex justify-content-center align-items-center w-100">
				<input type="submit" value="Rechercher" class="btn btn-lg">
			</section>
		</form>
		<ul class="d-flex justify-content-between flex-wrap p-0">
			<li class="card p-2 mb-3" style="width: 40rem;">
				<section class="d-flex justify-content-between">
					<img src="image1.png" alt="Image article" style="width: 15rem; height: 15rem;">
					<section style="width: 20rem;">
						<a href="#">Nom de l'article</a>
						<p>Prix : 123 points</p>
						<p>Fin de l'enchère : 01/01/2024</p>
						<p>Vendeur : <a href="#">Vendeur1</a></p>
					</section>
				</section>
			</li>
			<li class="card p-2 mb-3" style="width: 40rem;">
				<section class="d-flex justify-content-between">
					<img src="image1.png" alt="Image article" style="width: 15rem; height: 15rem;">
					<section style="width: 20rem;">
						<a href="#">Nom de l'article</a>
						<p>Prix : 123 points</p>
						<p>Fin de l'enchère : 01/01/2024</p>
						<p>Vendeur : <a href="#">Vendeur1</a></p>
					</section>
				</section>
			</li>
			<li class="card p-2 mb-3" style="width: 40rem;">
				<section class="d-flex justify-content-between">
					<img src="image1.png" alt="Image article" style="width: 15rem; height: 15rem;">
					<section style="width: 20rem;">
						<a href="#">Nom de l'article</a>
						<p>Prix : 123 points</p>
						<p>Fin de l'enchère : 01/01/2024</p>
						<p>Vendeur : <a href="#">Vendeur1</a></p>
					</section>
				</section>
			</li>
		</ul>
	</main>
</body>
</html>
