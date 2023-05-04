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
						"/join",
						"/account",
						"/update-account",
						"/delete-account",
						"/seller"
		})
public class UtilisateurServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public UtilisateurServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	  	if (request.getServletPath().equals("/join") || request.getServletPath().equals("/update-account")) {
		  	request.getRequestDispatcher("/WEB-INF/update-account.jsp").forward(request, response);
		} else if (request.getServletPath().equals("/account")) {
			displayUtilisateur(request, response);
		} else if (request.getServletPath().equals("/delete-account")) {
			deleteUtilisateur(request, response);
		} else if (request.getServletPath().equals("/seller")) {
			displayVendeur(request, response);
		}
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	  	if (request.getServletPath().equals("/join")) {
	  		addUtilisateur(request, response);
		} else if (request.getServletPath().equals("/update-account")) {
			updateUtilisateur(request, response);
		}
  }
  
  private static void addUtilisateur(HttpServletRequest request, HttpServletResponse response) {
	  Utilisateur utilisateur = new Utilisateur(0,
			  request.getParameter("pseudo"),
			  request.getParameter("nom"),
			  request.getParameter("prenom"),
			  request.getParameter("email"),
			  request.getParameter("telephone"),
			  request.getParameter("rue"),
			  request.getParameter("codePostal"),
			  request.getParameter("ville"),
			  request.getParameter("password"),
			  0,
			  (byte) 0,
			  null);
	  String utilisateurNotOk = UtilisateurManager.getInstance().addUtilisateur(utilisateur);
	  
	  try {
		  if (utilisateurNotOk == null) {
			  request.getSession().setAttribute("utilisateur", utilisateur);
			  response.sendRedirect("encheres-list");
		  } else {
			  request.setAttribute("accountCreationError", utilisateurNotOk);
			  request.getRequestDispatcher("/WEB-INF/update-account.jsp").forward(request, response);
		  }
		} catch (Exception e) {
			e.printStackTrace();
		}
  }
  
  private static void updateUtilisateur(HttpServletRequest request, HttpServletResponse response) {	  
	  Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
	  int noUtilisateur = utilisateur.getNoUtilisateur();
	  String actualPassword = UtilisateurManager.encryptPassword(request.getParameter("actualPassword"));
	  boolean actualPasswordOk = UtilisateurManager.getInstance().checkPassword(noUtilisateur, actualPassword);
	  if (actualPasswordOk) {
		  Utilisateur newData = new Utilisateur(noUtilisateur,
				  request.getParameter("pseudo"),
				  request.getParameter("nom"),
				  request.getParameter("prenom"),
				  request.getParameter("email"),
				  request.getParameter("telephone"),
				  request.getParameter("rue"),
				  request.getParameter("codePostal"),
				  request.getParameter("ville"),
				  null,
				  utilisateur.getCredit(),
				  utilisateur.getAdministrateur(),
				  null);
		  String newPassword = request.getParameter("password") == null || request.getParameter("password") == "" ? actualPassword : UtilisateurManager.encryptPassword(request.getParameter("password"));
		  newData.setMotDePasse(newPassword);
		  boolean checkUniquePseudo = !utilisateur.getPseudo().equals(newData.getPseudo());
		  boolean checkUniqueEmail = !utilisateur.getEmail().equals(newData.getEmail());
		  String utilisateurNotUpdated = UtilisateurManager.getInstance().updateUtilisateur(newData, checkUniquePseudo, checkUniqueEmail);
		  try {
		 	  if (utilisateurNotUpdated == null) {
		 		 request.getSession().setAttribute("utilisateur", newData);
				 response.sendRedirect("encheres-list");
		 	  } else {
		 		 request.setAttribute("accountUpdateError", utilisateurNotUpdated);
				 request.getRequestDispatcher("/WEB-INF/update-account.jsp").forward(request, response);
		 	  }
		  } catch (Exception e) {
				e.printStackTrace();
		  }
	  } else {
		  try {
			  request.setAttribute("accountUpdateError", "Le mot de passe actuel est incorrect");
			  request.getRequestDispatcher("/WEB-INF/update-account.jsp").forward(request, response);
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	  }
  }
  
  private static void deleteUtilisateur(HttpServletRequest request, HttpServletResponse response) {
	  Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
	  UtilisateurManager.getInstance().deleteUtilisateur(utilisateur.getNoUtilisateur());
	  try {
		response.sendRedirect("logout");
	  } catch (IOException e) {
		e.printStackTrace();
	}
  }
  
  private static void displayUtilisateur(HttpServletRequest request, HttpServletResponse response) {
	  request.setAttribute("account", request.getSession().getAttribute("utilisateur"));
	  	try {
	  		request.getRequestDispatcher("/WEB-INF/account.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
  }
  
  private static void displayVendeur(HttpServletRequest request, HttpServletResponse response) {
		String vendeurPseudo = request.getParameter("pseudo");
		Utilisateur vendeur = UtilisateurManager.getInstance().getUtilisateurByPseudo(vendeurPseudo);
		request.setAttribute("account", vendeur);
	  	try {
	  		request.getRequestDispatcher("/WEB-INF/account.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
  }
}