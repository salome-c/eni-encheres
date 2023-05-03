package fr.eni.javaee.encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.eni.javaee.encheres.bll.ArticleVenduManager;
import fr.eni.javaee.encheres.bll.UtilisateurManager;
import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.Utilisateur;

@WebServlet("/liste-encheres")
public class ListeEncheresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ListeEncheresServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    	      throws ServletException, IOException {
    	afficherEncheres(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    	      throws ServletException, IOException {
    }
    
    private static void afficherEncheres(HttpServletRequest request, HttpServletResponse response) {
    	ArticleVendu[] articles = ArticleVenduManager.getInstance().getArticlesVendus();
    	ArrayList<Object> articlesEtVendeurs = new ArrayList<Object>();
    	for (ArticleVendu article : articles) {
    		Utilisateur vendeur = UtilisateurManager.getInstance().getUtilisateurPseudo(article.getNoUtilisateur());
    		Object[] articleEtVendeur = {article, vendeur};
    		articlesEtVendeurs.add(articleEtVendeur);
    	}
		request.getSession().setAttribute("articlesEtVendeurs", articlesEtVendeurs);
    	try {
			request.getRequestDispatcher("/WEB-INF/liste-encheres.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
