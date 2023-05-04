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
		<h1 class="m-0"><a href="${pageContext.request.contextPath}/encheres-list">ENI-Enchères</a></h1>
	</header>
	<main class="w-50 mx-auto mt-3">
		<form action="${pageContext.request.contextPath}/login" method="post" class="w-100">
			<fieldset class="d-flex justify-content-between my-3">
				<label for="identifiant" class="w-25">Identifiant :</label>
			    <input type="text" id="identifiant" name="identifiant" class="w-50"><br><br>
			</fieldset>
		    <fieldset class="d-flex justify-content-between my-3">
			    <label for="motdepasse" class="w-25">Mot de passe :</label>
			    <input type="password" id="motdepasse" name="motdepasse" class="w-50"><br><br>
		    </fieldset>
		    <section class="d-flex justify-content-around">
    		   	<input type="submit" value="Connexion" class="btn lg-btn">
    		   	<section class="d-flex flex-column">
    		   		<label><input type="checkbox" id="souvenir" name="souvenir">Se souvenir de moi</label>
    		   		 <a href="#">Mot de passe oublié</a><br><br>
    		   	</section>
		    </section>
  		</form>
		<c:if test="${not empty requestScope.connectionError}">
		 	<section class="my-3 text-center">${requestScope.connectionError}</section>
		</c:if>
  		<br>
  		<a href="${pageContext.request.contextPath}/join"><button class="btn btn-lg w-100">Créer un compte</button></a>
	</main>
</body>
</html>
