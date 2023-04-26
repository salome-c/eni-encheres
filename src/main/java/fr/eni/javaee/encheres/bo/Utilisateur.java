package fr.eni.javaee.encheres.bo;

import fr.eni.javaee.encheres.bll.UtilisateurManager;

public class Utilisateur {
	private String login;
	private String password;
	
	public Utilisateur(String login, String password) {
		this.login = login;
		this.password = UtilisateurManager.crypterMotDePasse(password);
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public String getPassword() {
		return this.password;
	}
}
