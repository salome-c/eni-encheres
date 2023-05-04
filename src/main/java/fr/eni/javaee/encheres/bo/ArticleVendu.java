package fr.eni.javaee.encheres.bo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ArticleVendu {
	private int noArticle;
	private String nomArticle;
	private String description;
	private String dateDebutEncheres;
	private String dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private byte etatVente;
	private int noUtilisateur;
	private int noCategorie;
	
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	public ArticleVendu(int noArticle,
			String nomArticle,
			String description,
			Date dateDebutEncheres,
			Date dateFinEncheres,
			int miseAPrix,
			int prixVente,
			int noUtilisateur,
			int noCategorie) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = formatter.format(dateFinEncheres);
		this.dateFinEncheres = formatter.format(dateFinEncheres);
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
	}
	
	public ArticleVendu(String nomArticle,
			String description,
			int noCategorie,
			int miseAPrix,
			String dateDebutEncheres,
			String dateFinEncheres,
			int noUtilisateur) {
		this.nomArticle = nomArticle;
		this.description = description;
		this.noCategorie = noCategorie;
		this.miseAPrix = miseAPrix;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.noUtilisateur = noUtilisateur;
	}
	
	public int getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	public String getNomArticle() {
		return nomArticle;
	}
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	public void setDateDebutEncheres(String dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	public String getDateFinEncheres() {
		return dateFinEncheres;
	}
	public void setDateFinEncheres(String dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	public int getMiseAPrix() {
		return miseAPrix;
	}
	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}
	public int getPrixVente() {
		return prixVente;
	}
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}
	public byte getEtatVente() {
		return etatVente;
	}
	public void setEtatVente(byte etatVente) {
		this.etatVente = etatVente;
	}
	public int getNoUtilisateur() {
		return noUtilisateur;
	}
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	public int getNoCategorie() {
		return noCategorie;
	}
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}
}
