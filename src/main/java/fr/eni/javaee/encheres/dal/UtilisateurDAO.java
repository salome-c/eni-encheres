package fr.eni.javaee.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import fr.eni.javaee.encheres.bo.Utilisateur;

public class UtilisateurDAO implements IUtilisateurDAO {

	private static final String SELECT_UTILISATEUR = "SELECT * FROM UTILISATEURS WHERE (pseudo = ? OR email = ?) AND mot_de_passe = ?";
	private static final String INSERT_UTILISATEUR = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 0, 0)";
	private static final String SELECT_UTILISATEUR_BY_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo = ?";
	private static final String SELECT_UTILISATEUR_BY_EMAIL = "SELECT * FROM UTILISATEURS WHERE email = ?";

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
	
	@Override
	public void creerUtilisateur(Utilisateur utilisateur) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_UTILISATEUR, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, utilisateur.getPseudo());
				pstmt.setString(2, utilisateur.getNom());
				pstmt.setString(3, utilisateur.getPrenom());
				pstmt.setString(4, utilisateur.getEmail());
				pstmt.setString(5, utilisateur.getTelephone());
				pstmt.setString(6, utilisateur.getRue());
				pstmt.setString(7, utilisateur.getCodePostal());
				pstmt.setString(8, utilisateur.getVille());
				pstmt.setString(9, utilisateur.getMotDePasse());
				int res = pstmt.executeUpdate();
				if (res == 1) {
					ResultSet rs = pstmt.getGeneratedKeys();
					if (rs.next()) {
						utilisateur.setNoUtilisateur(rs.getInt(1));
						utilisateur.setCredit(0);
						utilisateur.setAdministrateur((byte) 0);
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
	}
	
	@Override
	public boolean rechercherPseudoExistant(String pseudo) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_UTILISATEUR_BY_PSEUDO);
				pstmt.setString(1, pseudo);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					return true;
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
		return false;
	}
	
	@Override
	public boolean rechercherEmailExistant(String email) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_UTILISATEUR_BY_EMAIL);
				pstmt.setString(1, email);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					return true;
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
		return false;
	}

}
