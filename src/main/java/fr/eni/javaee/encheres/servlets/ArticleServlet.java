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
						"/new-article",
						"/article"
		})
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ArticleServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getServletPath().equals("/new-article")) {
			displayNewArticle(request, response);
		} else if (request.getServletPath().equals("/article")) {
			displayArticleVendu(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		addArticleVendu(request, response);
	}
	
	private static void displayNewArticle(HttpServletRequest request, HttpServletResponse response) {
		Categorie[] categories = CategorieManager.getInstance().getCategories();
		request.setAttribute("categories", categories);
		try {
			request.getRequestDispatcher("/WEB-INF/new-article.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void addArticleVendu(HttpServletRequest request, HttpServletResponse response) {
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		ArticleVendu vente = new ArticleVendu(request.getParameter("nom"),
				request.getParameter("description"),
				Integer.parseInt(request.getParameter("categorie")),
				Integer.parseInt(request.getParameter("prix")),
				request.getParameter("dateDebutEnchere"),
				request.getParameter("dateFinEnchere"),
				utilisateur.getNoUtilisateur());
		String articleNotOk = ArticleVenduManager.getInstance().checkArticleVendu(vente);
		if (articleNotOk == null) {
			Retrait retrait = new Retrait(request.getParameter("rue"), request.getParameter("codePostal"), request.getParameter("ville"));
			String retraitNotOk = RetraitManager.getInstance().checkRetrait(retrait);
			if (retraitNotOk == null) {
				int noArticleVendu = ArticleVenduManager.getInstance().addArticleVendu(vente);
				retrait.setNoArticle(noArticleVendu);
				RetraitManager.getInstance().addRetrait(retrait);
				try {
					response.sendRedirect("encheres-list");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				request.setAttribute("venteOrRetraitCreationError", retraitNotOk);
				displayNewArticle(request, response);
			}
		} else {
			request.setAttribute("venteOrRetraitCreationError", articleNotOk);
			displayNewArticle(request, response);
		}
	}
	
	private static void displayArticleVendu(HttpServletRequest request, HttpServletResponse response) {
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
			request.getRequestDispatcher("/WEB-INF/article.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
