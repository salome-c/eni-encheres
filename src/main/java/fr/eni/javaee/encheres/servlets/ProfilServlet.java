package fr.eni.javaee.encheres.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.bll.UtilisateurManager;

@WebServlet("/profil")
public class ProfilServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public ProfilServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	  	request.getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	  	creerUtilisateur(request, response);
  }
  
  private static void creerUtilisateur(HttpServletRequest request, HttpServletResponse response) {
	  UtilisateurManager utilisateurManager = new UtilisateurManager();
	  Utilisateur utilisateur = new Utilisateur(request.getParameter("pseudo"),
			  request.getParameter("nom"),
			  request.getParameter("prenom"),
			  request.getParameter("email"),
			  request.getParameter("telephone"),
			  request.getParameter("rue"),
			  request.getParameter("codePostal"),
			  request.getParameter("ville"),
			  request.getParameter("password"));
	  
	  try {
		  String infoUtilisateurNonCree = utilisateurManager.creerUtilisateur(utilisateur);
		  if (infoUtilisateurNonCree == null) {
			  request.getSession().setAttribute("utilisateur", utilisateur);
			  response.sendRedirect("liste-encheres");
		  } else {
			  request.setAttribute("erreurCreationProfil", infoUtilisateurNonCree);
			  request.getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);
		  }
		} catch (Exception e) {
			e.printStackTrace();
		}
  }
}