<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ENI-Enchères</title>
</head>
<body>
	<h1>ENI-Enchères</h1>
	<h2>Mon profil</h2>

	<form action="${pageContext.request.contextPath}/profil" method="post">
		<label for="pseudo">Pseudo* :</label>
		<input type="text" pattern="[a-zA-Z0-9]+" id="pseudo" name="pseudo" required><br>

		<label for="nom">Nom* :</label>
		<input type="text" id="nom" name="nom" required><br>

		<label for="prenom">Prénom* :</label>
		<input type="text" id="prenom" name="prenom" required><br>

		<label for="email">Email* :</label>
		<input type="email" id="email" name="email" required><br>

		<label for="telephone">Téléphone :</label>
		<input type="tel" pattern="0[1-9]{1}[0-9]{8}" id="telephone" name="telephone"><br>

		<label for="rue">Rue* :</label>
		<input type="text" id="rue" name="rue" required><br>

		<label for="codePostal">Code postal* :</label>
		<input type="text" pattern="[0-9]{1}[1-9]{1}[0-9]{3}" id="codePostal" name="codePostal" required><br>

		<label for="ville">Ville* :</label>
		<input type="text" id="ville" name="ville" required><br>

		<label for="password">Mot de passe* :</label>
		<input type="password" id="password" name="password" required><br>

		<label for="confirmPassword">Confirmation* :</label>
		<input type="password" id="confirmPassword" name="confirmPassword" required oninput="checkPasswordMatch()"><br>

		<div id="password-match"></div>

		<input type="submit" value="Créer">
		<input type="reset" value="Annuler">
		
		<c:if test="${not empty requestScope.erreurCreationProfil}">
		  <div>${requestScope.erreurCreationProfil}</div>
		</c:if>
	</form>
	
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
