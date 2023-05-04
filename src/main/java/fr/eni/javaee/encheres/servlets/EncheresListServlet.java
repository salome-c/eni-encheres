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

@WebServlet("/encheres-list")
public class EncheresListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EncheresListServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    	      throws ServletException, IOException {
    	displayEncheres(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    	      throws ServletException, IOException {
    }
    
    private static void displayEncheres(HttpServletRequest request, HttpServletResponse response) {
    	ArticleVendu[] articles = ArticleVenduManager.getInstance().getArticlesVendus();
    	ArrayList<Object> articlesAndVendeurs = new ArrayList<Object>();
    	for (ArticleVendu article : articles) {
    		Utilisateur vendeur = UtilisateurManager.getInstance().getUtilisateurPseudo(article.getNoUtilisateur());
    		Object[] articleAndVendeur = {article, vendeur};
    		articlesAndVendeurs.add(articleAndVendeur);
    	}
		request.setAttribute("articlesAndVendeurs", articlesAndVendeurs);
    	try {
			request.getRequestDispatcher("/WEB-INF/encheres-list.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
