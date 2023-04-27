package fr.eni.javaee.encheres.bll;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.dal.DAOFactory;
import fr.eni.javaee.encheres.dal.IUtilisateurDAO;

public class UtilisateurManager {
	private IUtilisateurDAO utilisateurDAO;
	
	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	public Utilisateur connecterUtilisateur(Utilisateur utilisateur) {
		return this.utilisateurDAO.connecterUtilisateur(utilisateur);
	}
	
	public static String crypterMotDePasse(String motDePasse) {
		MessageDigest md;
		StringBuffer sb = new StringBuffer();
		
		if (motDePasse != null) {
			try {
				md = MessageDigest.getInstance("SHA1");
				md.update((motDePasse).getBytes());
				byte[] passwordByte = md.digest();
				for (int i = 0; i < passwordByte.length; i++) {
				    sb.append(String.format("%02X", passwordByte[i] & 0xff));
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}
}
