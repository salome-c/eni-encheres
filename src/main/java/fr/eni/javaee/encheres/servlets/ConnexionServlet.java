package fr.eni.javaee.encheres.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.bll.UtilisateurManager;

@WebServlet(
		urlPatterns= {
						"/connexion",
						"/deconnexion"
		})
public class ConnexionServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public ConnexionServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
		if (request.getServletPath().equals("/connexion")) {
			request.getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
		} else if (request.getServletPath().equals("/deconnexion")) {
			deconnecterUtilisateur(request, response);
		}
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
		if (request.getServletPath().equals("/connexion")) {
			connecterUtilisateur(request, response);
		}
  }
  
  private static void connecterUtilisateur(HttpServletRequest request, HttpServletResponse response) {
	  Utilisateur utilisateur = new Utilisateur(request.getParameter("identifiant"), request.getParameter("motdepasse"));
	  Utilisateur utilisateurConnecte = UtilisateurManager.getInstance().connecterUtilisateur(utilisateur);
  
	  try {
		  if (utilisateurConnecte != null) {
			  request.getSession().setAttribute("utilisateur", utilisateurConnecte);
			  response.sendRedirect("liste-encheres");
		  } else {
			  request.setAttribute("erreurConnexion", "Identifiant ou mot de passe incorrect !");
			  request.getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
		  }
		} catch (Exception e) {
			e.printStackTrace();
		}
  }
  
  private static void deconnecterUtilisateur(HttpServletRequest request, HttpServletResponse response) {
	  try {
		  request.getSession().invalidate();
		  response.sendRedirect("liste-encheres");
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
  }
}