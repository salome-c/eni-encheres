<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ENI-Enchères</title>
</head>
<body>
	<header>
		<h1>ENI-Enchères</h1>
		<nav>
			<ul>
				<li><a href="#">Enchères</a></li>
				<li><a href="#">Vendre un article</a></li>
				<li><a href="#">Mon profil</a></li>
				<li><a href="#">Déconnexion</a></li>
			</ul>
		</nav>
	</header>
	<main>
		<h2>Liste des enchères</h2>
		<form name="filtres">
			<h3>Filtres :</h3>
			<input type="text" placeholder="Le nom de l'article contient">
			<label>Catégorie :</label>
			<select>
				<option selected>Toutes</option>
				<option>Catégorie 1</option>
				<option>Catégorie 2</option>
				<option>Catégorie 3</option>
			</select>
			<fieldset>
				<label><input type="radio" name="filtre">Achats</label>
				<label><input type="checkbox" name="achats">Enchères ouvertes</label>
				<label><input type="checkbox" name="achats">Mes enchères en cours</label>
				<label><input type="checkbox" name="achats">Mes enchères remportées</label>
				<label><input type="radio" name="filtre">Mes ventes</label>
				<label><input type="checkbox" name="ventes">Mes ventes en cours</label>
				<label><input type="checkbox" name="ventes">Ventes non débutées</label>
				<label><input type="checkbox" name="ventes">Ventes terminées</label>
			</fieldset>
			<input type="submit" value="Rechercher">
		</form>
		<p><%= session.getAttribute("pseudoUtilisateur") %> est connecté</p>
		<ul>
			<li>
				<div>
					<img src="image1.png" alt="Image article">
					<h3><a href="#">Nom de l'article</a></h3>
					<p>Prix : 123 points</p>
					<p>Fin de l'enchère : 01/01/2024</p>
					<p>Vendeur : <a href="#">Vendeur1</a></p>
				</div>
			</li>
			<li>
				<div>
					<img src="image2.png" alt="Image article">
					<h3><a href="#">Nom de l'article</a></h3>
					<p>Prix : 456 points</p>
					<p>Fin de l'enchère : 02/01/2024</p>
					<p>Vendeur : <a href="#">Vendeur2</a></p>
				</div>
			</li>
		</ul>
	</main>
</body>
</html>
