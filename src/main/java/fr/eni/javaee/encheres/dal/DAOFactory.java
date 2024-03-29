package fr.eni.javaee.encheres.dal;

public abstract class DAOFactory {
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAO();
	}
	
	public static ArticleVenduDAO getArticleVenduDAO() {
		return new ArticleVenduDAO();
	}
	
	public static RetraitDAO getRetraitDAO() {
		return new RetraitDAO();
	}
	
	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAO();
	}
}
