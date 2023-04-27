package fr.eni.javaee.encheres.bo;

import fr.eni.javaee.encheres.bll.UtilisateurManager;

public class Utilisateur {
	private int no_utilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String code_postal;
	private String ville;
	private String mot_de_passe;
	private int credit;
	private byte administrateur;
	private String identifiant;
	
	public Utilisateur(int no_utilisateur,
			String pseudo,
			String nom,
			String prenom,
			String email,
			String telephone,
			String rue,
			String code_postal,
			String ville,
			String mot_de_passe,
			int credit,
			byte administrateur,
			String identifiant) {
		this.no_utilisateur = no_utilisateur;
		this.pseudo = pseudo;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.mot_de_passe = UtilisateurManager.crypterMotDePasse(mot_de_passe);
		this.credit = credit;
		this.administrateur = administrateur;
		this.identifiant = identifiant;
	}
	
	public Utilisateur(String identifiant, String motDePasse) {
		this.identifiant = identifiant;
		this.mot_de_passe = UtilisateurManager.crypterMotDePasse(motDePasse);
	}

	public String getIdentifiant() {
		return this.identifiant;
	}
	
	public String getMotDePasse() {
		return this.mot_de_passe;
	}
	
	public String getPseudo() {
		return this.pseudo;
	}
}
