package fr.eni.javaee.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import fr.eni.javaee.encheres.bo.Categorie;

public class CategorieDAO implements ICategorieDAO {
	private static final String SELECT_CATEGORIES = "SELECT * FROM CATEGORIES";
	private static final String SELECT_CATEGORIE = "SELECT * FROM CATEGORIES WHERE no_categorie = ?";

	@Override
	public Categorie[] getCategories() {
		ArrayList<Categorie> categories = new ArrayList<Categorie>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_CATEGORIES);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					Categorie categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
					categories.add(categorie);
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
		return categories.toArray(new Categorie[categories.size()]);
	}
	
	@Override
	public Categorie getCategorie(int noCategorie) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_CATEGORIE);
				pstmt.setInt(1, noCategorie);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					return new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
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
