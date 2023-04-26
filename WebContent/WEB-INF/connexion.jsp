<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>ENI-Enchères</title>
  </head>
  <body>
    <h1>ENI-Enchères</h1>
    <form action="${pageContext.request.contextPath}/connexion" method="post">
      <label for="identifiant">Identifiant :</label>
      <input type="text" id="identifiant" name="identifiant"><br><br>
      <label for="motdepasse">Mot de passe :</label>
      <input type="password" id="motdepasse" name="motdepasse"><br><br>
      <input type="checkbox" id="souvenir" name="souvenir">
      <label for="souvenir">Se souvenir de moi</label><br><br>
      <input type="submit" value="Connexion">
    </form>
    <br>
    <a href="#">Mot de passe oublié</a><br><br>
    <button onclick="window.location.href='page_creation_compte.jsp'">Créer un compte</button>
  </body>
</html>
