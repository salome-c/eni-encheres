package fr.eni.javaee.encheres.dal;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.javaee.encheres.bo.Utilisateur;

public class UtilisateurDAO implements IUtilisateurDAO {
	
	private static final String SELECT_UTILISATEUR = "SELECT id, login FROM utilisateurs WHERE login = ? AND password = ?";

	@Override
	public void connecterUtilisateur(Utilisateur utilisateur) {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_UTILISATEUR);
				pstmt.setString(1, utilisateur.getLogin());
				pstmt.setString(2, utilisateur.getPassword());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					System.out.println("Utilisateur connecté : " + rs.getString("login"));
				} else {
					System.out.println("L'identifiant ou le mot de passe est incorrect");
				}
				pstmt.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
