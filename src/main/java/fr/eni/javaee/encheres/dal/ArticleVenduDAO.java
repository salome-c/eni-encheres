package fr.eni.javaee.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import fr.eni.javaee.encheres.bo.ArticleVendu;

public class ArticleVenduDAO implements IArticleVenduDAO {
	private static final String SELECT_ARTICLES = "SELECT * FROM ARTICLES_VENDUS";

	@Override
	public ArticleVendu[] getArticlesVendus() {
		ArrayList<ArticleVendu> articles = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ARTICLES);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					ArticleVendu article = new ArticleVendu(rs.getInt("no_article"),
							rs.getString("nom_article"),
							rs.getString("description"),
							rs.getDate("date_debut_encheres"),
							rs.getDate("date_fin_encheres"),
							rs.getInt("prix_initial"),
							rs.getInt("prix_vente"),
							rs.getInt("no_utilisateur"),
							rs.getInt("no_categorie"));
					articles.add(article);
				}
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articles.toArray(new ArticleVendu[articles.size()]);
	}
}
