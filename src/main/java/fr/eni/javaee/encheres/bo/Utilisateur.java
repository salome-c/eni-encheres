package fr.eni.javaee.encheres.bo;

import fr.eni.javaee.encheres.bll.UtilisateurManager;

public class Utilisateur {
	private int noUtilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String codePostal;
	private String ville;
	private String motDePasse;
	private int credit;
	private byte administrateur;
	private String identifiant;
	
	public Utilisateur(int noUtilisateur,
			String pseudo,
			String nom,
			String prenom,
			String email,
			String telephone,
			String rue,
			String codePostal,
			String ville,
			String motDePasse,
			int credit,
			byte administrateur,
			String identifiant) {
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = UtilisateurManager.encryptPassword(motDePasse);
		this.credit = credit;
		this.administrateur = administrateur;
		this.identifiant = identifiant;
	}
	
	public Utilisateur(String identifiant, String motDePasse) {
		this.identifiant = identifiant;
		this.motDePasse = UtilisateurManager.encryptPassword(motDePasse);
	}
	
	public Utilisateur(String pseudo,
			String nom,
			String prenom,
			String email,
			String telephone,
			String rue,
			String codePostal,
			String ville) {
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	public Utilisateur(int noUtilisateur, String pseudo) {
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
	}
	
	public int getNoUtilisateur() {
		return this.noUtilisateur;
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
		return this.motDePasse;
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
		return this.codePostal;
	}
	public String getVille() {
		return this.ville;
	}
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
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
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
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
