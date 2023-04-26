package fr.eni.javaee.encheres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.bll.UtilisateurManager;

@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public ConnexionServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	  connecterUtilisateur(request, response);
  }
  
  private void connecterUtilisateur(HttpServletRequest request, HttpServletResponse response) {
	  UtilisateurManager utilisateurManager = new UtilisateurManager();
	  Utilisateur utilisateur = new Utilisateur(request.getParameter("identifiant"), request.getParameter("motdepasse"));
	  
	  try {
		  utilisateurManager.connecterUtilisateur(utilisateur);
		} catch (Exception e) {
			e.printStackTrace();
		}
  }
}