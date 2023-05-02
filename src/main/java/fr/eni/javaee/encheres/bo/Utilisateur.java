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
		this.nom = nom;
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
	
	public Utilisateur(String pseudo,
			String nom,
			String prenom,
			String email,
			String telephone,
			String rue,
			String code_postal,
			String ville,
			String mot_de_passe) {
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.mot_de_passe = UtilisateurManager.crypterMotDePasse(mot_de_passe);
	}
	
	public Utilisateur(String pseudo,
			String nom,
			String prenom,
			String email,
			String telephone,
			String rue,
			String code_postal,
			String ville) {
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
	}
	
	public Utilisateur(int no_utilisateur, String pseudo) {
		this.no_utilisateur = no_utilisateur;
		this.pseudo = pseudo;
	}
	
	public int getNoUtilisateur() {
		return this.no_utilisateur;
	}

	public int getCredit() {
		return this.credit;
	}

	public byte getAdministrateur() {
		return this.administrateur;
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
	
	public String getNom() {
		return this.nom;
	}
	
	public String getPrenom() {
		return this.prenom;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getTelephone() {
		return this.telephone;
	}
	
	public String getRue() {
		return this.rue;
	}
	
	public String getCodePostal() {
		return this.code_postal;
	}
	
	public String getVille() {
		return this.ville;
	}

	public void setNoUtilisateur(int no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public void setCodePostal(String code_postal) {
		this.code_postal = code_postal;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public void setMotDePasse(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public void setAdministrateur(byte administrateur) {
		this.administrateur = administrateur;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
}
