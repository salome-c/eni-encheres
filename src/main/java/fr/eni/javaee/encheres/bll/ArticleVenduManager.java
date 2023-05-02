package fr.eni.javaee.encheres.bll;

import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.dal.DAOFactory;
import fr.eni.javaee.encheres.dal.IArticleVenduDAO;

public class ArticleVenduManager {
	private IArticleVenduDAO articleVenduDAO;
	
	public ArticleVenduManager() {
		this.articleVenduDAO = DAOFactory.getArticleVenduDAO();
	}
	
	public ArticleVendu[] getArticlesVendus() {
		return articleVenduDAO.getArticlesVendus();
	}

}
