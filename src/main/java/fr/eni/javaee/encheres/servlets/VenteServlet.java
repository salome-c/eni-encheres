package fr.eni.javaee.encheres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.javaee.encheres.bll.ArticleVenduManager;
import fr.eni.javaee.encheres.bll.CategorieManager;
import fr.eni.javaee.encheres.bll.RetraitManager;
import fr.eni.javaee.encheres.bll.UtilisateurManager;
import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.Categorie;
import fr.eni.javaee.encheres.bo.Retrait;
import fr.eni.javaee.encheres.bo.Utilisateur;

@WebServlet(
		urlPatterns= {
						"/nouvelle-vente",
						"/vente"
		})
public class VenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VenteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getServletPath().equals("/nouvelle-vente")) {
			afficherFormulaireNouvelleVente(request, response);
		} else if (request.getServletPath().equals("/vente")) {
			afficherDetailVente(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ajouterNouvelleVente(request, response);
	}
	
	private static void afficherFormulaireNouvelleVente(HttpServletRequest request, HttpServletResponse response) {
		Categorie[] categories = CategorieManager.getInstance().getCategories();
		request.getSession().setAttribute("categories", categories);
		try {
			request.getRequestDispatcher("/WEB-INF/nouvelle-vente.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void ajouterNouvelleVente(HttpServletRequest request, HttpServletResponse response) {
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		ArticleVendu vente = new ArticleVendu(request.getParameter("nom"),
				request.getParameter("description"),
				Integer.parseInt(request.getParameter("categorie")),
				Integer.parseInt(request.getParameter("prix")),
				request.getParameter("dateDebutEnchere"),
				request.getParameter("dateFinEnchere"),
				utilisateur.getNoUtilisateur());
		String infoVenteNonValide = ArticleVenduManager.getInstance().validerVente(vente);
		if (infoVenteNonValide == null) {
			Retrait retrait = new Retrait(request.getParameter("rue"), request.getParameter("codePostal"), request.getParameter("ville"));
			String infoRetraitNonValide = RetraitManager.getInstance().validerRetrait(retrait);
			if (infoRetraitNonValide == null) {
				int noArticleVendu = ArticleVenduManager.getInstance().creerVente(vente);
				retrait.setNoArticle(noArticleVendu);
				RetraitManager.getInstance().creerRetrait(retrait);
				try {
					response.sendRedirect("liste-encheres");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				request.setAttribute("erreurCreationVenteOuRetrait", infoRetraitNonValide);
				try {
					request.getRequestDispatcher("/WEB-INF/nouvelle-vente.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			request.setAttribute("erreurCreationVenteOuRetrait", infoVenteNonValide);
			try {
				request.getRequestDispatcher("/WEB-INF/nouvelle-vente.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void afficherDetailVente(HttpServletRequest request, HttpServletResponse response) {
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		ArticleVendu article = ArticleVenduManager.getInstance().getArticleVendu(noArticle);
		Categorie categorie = CategorieManager.getInstance().getCategorie(article.getNoCategorie());
		Retrait retrait = RetraitManager.getInstance().getRetrait(noArticle);
		Utilisateur vendeur = UtilisateurManager.getInstance().getUtilisateurPseudo(article.getNoUtilisateur());
		request.setAttribute("article", article);
		request.setAttribute("categorie", categorie);
		request.setAttribute("retrait", retrait);
		request.setAttribute("vendeur", vendeur);
		try {
			request.getRequestDispatcher("/WEB-INF/vente.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
