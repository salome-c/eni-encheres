package fr.eni.javaee.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import fr.eni.javaee.encheres.bo.ArticleVendu;

public class ArticleVenduDAO implements IArticleVenduDAO {
	private static final String SELECT_ARTICLES = "SELECT * FROM ARTICLES_VENDUS";
	private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS VALUES(?, ?, ?, ?, ?, NULL, ?, ?)";
	private static final String SELECT_ARTICLE_BY_NO = "SELECT * FROM ARTICLES_VENDUS WHERE no_article = ?";
	
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
	
	@Override
	public int addArticleVendu(ArticleVendu article) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLE, Statement.RETURN_GENERATED_KEYS);				
				pstmt.setString(1, article.getNomArticle());
				pstmt.setString(2, article.getDescription());
				pstmt.setString(3, article.getDateDebutEncheres());
				pstmt.setString(4, article.getDateFinEncheres());
				pstmt.setInt(5, article.getMiseAPrix());
				pstmt.setInt(6, article.getNoUtilisateur());
				pstmt.setInt(7, article.getNoCategorie());
				int res = pstmt.executeUpdate();
				if (res == 1) {
					ResultSet rs = pstmt.getGeneratedKeys();
					if (rs.next()) {
						return rs.getInt(1);
					}
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
		return 0;
	}
	
	@Override
	public ArticleVendu getArticleVendu(int noArticle) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ARTICLE_BY_NO);
				pstmt.setInt(1, noArticle);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					return new ArticleVendu(rs.getInt("no_article"),
							rs.getString("nom_article"),
							rs.getString("description"),
							rs.getDate("date_debut_encheres"),
							rs.getDate("date_fin_encheres"),
							rs.getInt("prix_initial"),
							rs.getInt("prix_vente"),
							rs.getInt("no_utilisateur"),
							rs.getInt("no_categorie"));
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
		return null;
	}
}
