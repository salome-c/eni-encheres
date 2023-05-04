package fr.eni.javaee.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.javaee.encheres.bo.Retrait;

public class RetraitDAO implements IRetraitDAO {
	private static final String INSERT_RETRAIT = "INSERT INTO RETRAITS VALUES(?, ?, ?, ?)";
	private static final String SELECT_RETRAIT_BY_NO = "SELECT * FROM RETRAITS WHERE no_article = ?";

	@Override
	public void addRetrait(Retrait retrait) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_RETRAIT);
				pstmt.setInt(1, retrait.getNoArticle());
				pstmt.setString(2, retrait.getRue());
				pstmt.setString(3, retrait.getCodePostal());
				pstmt.setString(4, retrait.getVille());
				pstmt.executeUpdate();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Retrait getRetrait(int noArticle) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_RETRAIT_BY_NO);
				pstmt.setInt(1, noArticle);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					return new Retrait(rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"));
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
