package fr.eni.javaee.encheres.dal;

public abstract class DAOFactory {
	public static UtilisateurDAO getUtilisateurDAO()
	{
		return new UtilisateurDAO();
	}
}