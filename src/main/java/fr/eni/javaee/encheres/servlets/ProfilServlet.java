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
						"/inscription",
						"/profil",
						"/modifier-profil",
						"/supprimer-profil",
						"/vendeur"
		})
public class ProfilServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public ProfilServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	  	if (request.getServletPath().equals("/inscription") || request.getServletPath().equals("/modifier-profil")) {
		  	request.getRequestDispatcher("/WEB-INF/modifier-profil.jsp").forward(request, response);
		} else if (request.getServletPath().equals("/profil")) {
			afficherProfilUtilisateur(request, response);
		} else if (request.getServletPath().equals("/supprimer-profil")) {
			supprimerUtilisateur(request, response);
		} else if (request.getServletPath().equals("/vendeur")) {
			afficherProfilVendeur(request, response);
		}
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	  	if (request.getServletPath().equals("/inscription")) {
		  	creerUtilisateur(request, response);
		} else if (request.getServletPath().equals("/modifier-profil")) {
			modifierUtilisateur(request, response);
		}
  }
  
  private static void creerUtilisateur(HttpServletRequest request, HttpServletResponse response) {
	  Utilisateur utilisateur = new Utilisateur(request.getParameter("pseudo"),
			  request.getParameter("nom"),
			  request.getParameter("prenom"),
			  request.getParameter("email"),
			  request.getParameter("telephone"),
			  request.getParameter("rue"),
			  request.getParameter("codePostal"),
			  request.getParameter("ville"),
			  request.getParameter("password"));
	  String infoUtilisateurNonCree = UtilisateurManager.getInstance().creerUtilisateur(utilisateur);
	  
	  try {
		  if (infoUtilisateurNonCree == null) {
			  request.getSession().setAttribute("utilisateur", utilisateur);
			  response.sendRedirect("liste-encheres");
		  } else {
			  request.setAttribute("erreurCreationProfil", infoUtilisateurNonCree);
			  request.getRequestDispatcher("/WEB-INF/modifier-profil.jsp").forward(request, response);
		  }
		} catch (Exception e) {
			e.printStackTrace();
		}
  }
  
  private static void modifierUtilisateur(HttpServletRequest request, HttpServletResponse response) {	  
	  Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
	  int noUtilisateur = utilisateur.getNoUtilisateur();
	  String motDePasseActuel = UtilisateurManager.crypterMotDePasse(request.getParameter("actualPassword"));
	  boolean motDePasseActuelValide = UtilisateurManager.getInstance().verifierMotDePasse(noUtilisateur, motDePasseActuel);
	  if (motDePasseActuelValide) {
		  Utilisateur nouvellesInfos = new Utilisateur(request.getParameter("pseudo"),
				  request.getParameter("nom"),
				  request.getParameter("prenom"),
				  request.getParameter("email"),
				  request.getParameter("telephone"),
				  request.getParameter("rue"),
				  request.getParameter("codePostal"),
				  request.getParameter("ville"),
				  null);
		  nouvellesInfos.setNoUtilisateur(noUtilisateur);
		  String nouveauMotDePasse = request.getParameter("password") == null || request.getParameter("password") == "" ? motDePasseActuel : UtilisateurManager.crypterMotDePasse(request.getParameter("password"));
		  nouvellesInfos.setMotDePasse(nouveauMotDePasse);
		  boolean verifierPseudoUnique = !utilisateur.getPseudo().equals(nouvellesInfos.getPseudo());
		  boolean verifierEmailUnique = !utilisateur.getEmail().equals(nouvellesInfos.getEmail());
		  String infoUtilisateurNonModifie = UtilisateurManager.getInstance().modifierUtilisateur(nouvellesInfos, verifierPseudoUnique, verifierEmailUnique);
		  try {
		 	  if (infoUtilisateurNonModifie == null) {
		 		 request.getSession().setAttribute("utilisateur", nouvellesInfos);
				 response.sendRedirect("liste-encheres");
		 	  } else {
		 		 request.setAttribute("erreurModificationProfil", infoUtilisateurNonModifie);
				 request.getRequestDispatcher("/WEB-INF/modifier-profil.jsp").forward(request, response);
		 	  }
		  } catch (Exception e) {
				e.printStackTrace();
		  }
	  } else {
		  try {
			  request.setAttribute("erreurModificationProfil", "Le mot de passe actuel est incorrect");
			  request.getRequestDispatcher("/WEB-INF/modifier-profil.jsp").forward(request, response);
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	  }
  }
  
  private static void supprimerUtilisateur(HttpServletRequest request, HttpServletResponse response) {
	  Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
	  UtilisateurManager.getInstance().supprimerUtilisateur(utilisateur.getNoUtilisateur());
	  try {
		response.sendRedirect("deconnexion");
	  } catch (IOException e) {
		e.printStackTrace();
	}
  }
  
  private static void afficherProfilUtilisateur(HttpServletRequest request, HttpServletResponse response) {
	  request.getSession().setAttribute("profil", request.getSession().getAttribute("utilisateur"));
	  	try {
	  		request.getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
  }
  
  private static void afficherProfilVendeur(HttpServletRequest request, HttpServletResponse response) {
		String vendeurPseudo = request.getParameter("pseudo");
		Utilisateur vendeur = UtilisateurManager.getInstance().getUtilisateurByPseudo(vendeurPseudo);
		request.getSession().setAttribute("profil", vendeur);
	  	try {
	  		request.getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
  }
}