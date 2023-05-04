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
						"/login",
						"/logout"
		})
public class ConnexionServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public ConnexionServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
		if (request.getServletPath().equals("/login")) {
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		} else if (request.getServletPath().equals("/logout")) {
			disconnectUtilisateur(request, response);
		}
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
		if (request.getServletPath().equals("/login")) {
			connectUtilisateur(request, response);
		}
  }
  
  private static void connectUtilisateur(HttpServletRequest request, HttpServletResponse response) {
	  Utilisateur utilisateur = new Utilisateur(request.getParameter("identifiant"), request.getParameter("motdepasse"));
	  Utilisateur loggedUtilisateur = UtilisateurManager.getInstance().connectUtilisateur(utilisateur);
	  try {
		  if (loggedUtilisateur != null) {
			  request.getSession().setAttribute("utilisateur", loggedUtilisateur);
			  response.sendRedirect("encheres-list");
		  } else {
			  request.setAttribute("connectionError", "Identifiant ou mot de passe incorrect !");
			  request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		  }
		} catch (Exception e) {
			e.printStackTrace();
		}
  }
  
  private static void disconnectUtilisateur(HttpServletRequest request, HttpServletResponse response) {
	  try {
		  request.getSession().invalidate();
		  response.sendRedirect("encheres-list");
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
  }
}