package fr.eni.javaee.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.javaee.encheres.bo.Utilisateur;

public class UtilisateurDAO implements IUtilisateurDAO {

	private static final String SELECT_UTILISATEUR = "SELECT * FROM UTILISATEURS WHERE (pseudo = ? OR email = ?) AND mot_de_passe = ?";

	@Override
	public Utilisateur connecterUtilisateur(Utilisateur utilisateur) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_UTILISATEUR);
				pstmt.setString(1, utilisateur.getIdentifiant());
				pstmt.setString(2, utilisateur.getIdentifiant());
				pstmt.setString(3, utilisateur.getMotDePasse());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					return new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"),
							rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"),
							rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"),
							rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getByte("administrateur"),
							utilisateur.getIdentifiant());
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
