package fr.eni.javaee.encheres.dal;

import fr.eni.javaee.encheres.bo.ArticleVendu;

public interface IArticleVenduDAO {
	public ArticleVendu[] getArticlesVendus();
	public int creerVente(ArticleVendu article);
	public ArticleVendu getArticleVendu(int noArticle);
}