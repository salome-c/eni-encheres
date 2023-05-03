package fr.eni.javaee.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;

import fr.eni.javaee.encheres.bo.Retrait;

public class RetraitDAO implements IRetraitDAO {
	private static final String INSERT_RETRAIT = "INSERT INTO RETRAITS VALUES(?, ?, ?, ?)";

	@Override
	public void creerRetrait(Retrait retrait) {
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
}
