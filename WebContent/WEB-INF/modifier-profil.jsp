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
	<main>
		<h2 class="text-center my-3">Mon profil</h2>
		<c:if test="${empty utilisateur}">
			<form action="${pageContext.request.contextPath}/inscription" method="post" class="w-75 mx-auto d-flex flex-wrap">
		</c:if>
		<c:if test="${not empty utilisateur}">
			<form action="" method="post" class="w-75 mx-auto d-flex flex-wrap">
		</c:if>
			<section class="w-50 d-flex justify-content-between my-3">
				<label for="pseudo" class="w-25">Pseudo* :</label>
				<input type="text" pattern="[a-zA-Z0-9]+" id="pseudo" name="pseudo" required class="w-50"><br>
			</section>
			
			<section class="w-50 d-flex justify-content-between my-3">
				<label for="nom" class="w-25">Nom* :</label>
				<input type="text" id="nom" name="nom" required class="w-50"><br>
			</section>
			
			<section class="w-50 d-flex justify-content-between my-3">
				<label for="prenom" class="w-25">Prénom* :</label>
				<input type="text" id="prenom" name="prenom" required class="w-50"><br>
			</section>
			
			<section class="w-50 d-flex justify-content-between my-3">
				<label for="email" class="w-25">Email* :</label>
				<input type="email" id="email" name="email" required class="w-50"><br>
			</section>
			
			<section class="w-50 d-flex justify-content-between my-3">
				<label for="telephone" class="w-25">Téléphone :</label>
				<input type="tel" pattern="0[1-9]{1}[0-9]{8}" id="telephone" name="telephone" class="w-50"><br>
			</section>
			
			<section class="w-50 d-flex justify-content-between my-3">
				<label for="rue" class="w-25">Rue* :</label>
				<input type="text" id="rue" name="rue" required class="w-50"><br>
			</section>
			
			<section class="w-50 d-flex justify-content-between my-3">
				<label for="codePostal" class="w-25">Code postal* :</label>
				<input type="text" pattern="[0-9]{1}[1-9]{1}[0-9]{3}" id="codePostal" name="codePostal" required class="w-50"><br>
			</section>
			
			<section class="w-50 d-flex justify-content-between my-3">
				<label for="ville" class="w-25">Ville* :</label>
				<input type="text" id="ville" name="ville" required class="w-50"><br>
			</section>
			
			<section class="w-50 d-flex justify-content-between my-3">
				<c:if test="${empty utilisateur}">
					<label for="password" class="w-25">Mot de passe* :</label>
				</c:if>
				<c:if test="${not empty utilisateur}">
					<label for="password" class="w-25">Nouveau mot de passe* :</label>
				</c:if>
				<input type="password" id="password" name="password" required class="w-50"><br>
			</section>
			
			<section class="w-50 d-flex justify-content-between my-3">
				<label for="confirmPassword" class="w-25">Confirmation* :</label>
				<input type="password" id="confirmPassword" name="confirmPassword" required oninput="checkPasswordMatch()" class="w-50"><br>
			</section>
		
			<section id="password-match" class="w-100 text-center"></section>
			
			<c:if test="${not empty utilisateur}">
				<section class="w-100">Crédit : ${utilisateur.credit}</section>
			</c:if>
			
			<c:if test="${empty utilisateur}">
				<section class="d-flex justify-content-around w-100">
					<input type="submit" value="Créer" class="btn btn-lg">
					<a href="${pageContext.request.contextPath}/liste-encheres"><input type="button" value="Annuler" class="btn btn-lg"></a>
				</section>
			</c:if>
			
			<c:if test="${not empty utilisateur}">
				<section class="d-flex justify-content-around w-100">
					<input value="Enregistrer" class="btn btn-lg">
					<input value="Supprimer mon compte" class="btn btn-lg">
				</section>
			</c:if>

			<c:if test="${not empty requestScope.erreurCreationProfil}">
			  <section class="w-100 text-center">${requestScope.erreurCreationProfil}</section>
			</c:if>
		</form>
	</main>
	<script>
		function checkPasswordMatch() {
		  var password = document.getElementById("password");
		  var confirm = document.getElementById("confirmPassword");
		  var match = document.getElementById("password-match");
		  
		  if (password.value == confirm.value) {
		    match.innerHTML = "Les mots de passe correspondent";
		    confirm.setCustomValidity("");
		  } else {
		    match.innerHTML = "Les mots de passe ne correspondent pas";
		    confirm.setCustomValidity("Les mots de passe ne correspondent pas");
		  }
		}
	</script>

</body>
</html>
