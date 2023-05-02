package fr.eni.javaee.encheres.dal;

import fr.eni.javaee.encheres.bo.ArticleVendu;

public interface IArticleVenduDAO {
	public ArticleVendu[] getArticlesVendus();
}