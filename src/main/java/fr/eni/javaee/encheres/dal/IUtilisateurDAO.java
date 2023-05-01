package fr.eni.javaee.encheres.dal;

import fr.eni.javaee.encheres.bo.Utilisateur;

public interface IUtilisateurDAO {
	public Utilisateur connecterUtilisateur(Utilisateur utilisateur);
	public void creerUtilisateur(Utilisateur utilisateur);
	public boolean rechercherPseudoExistant(String pseudo);
	public boolean rechercherEmailExistant(String email);
	public boolean verifierMotDePasse(int noUtilisateur, String motDePasse);
	public void modifierUtilisateur(Utilisateur nouvellesInfos);
	public void supprimerUtilisateur(int noUtilisateur);
}
