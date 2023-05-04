package fr.eni.javaee.encheres.dal;

import fr.eni.javaee.encheres.bo.Utilisateur;

public interface IUtilisateurDAO {
	public Utilisateur connectUtilisateur(Utilisateur utilisateur);
	public void addUtilisateur(Utilisateur utilisateur);
	public boolean searchExistingPseudo(String pseudo);
	public boolean searchExistingEmail(String email);
	public boolean checkPassword(int noUtilisateur, String motDePasse);
	public void updateUtilisateur(Utilisateur nouvellesInfos);
	public void deleteUtilisateur(int noUtilisateur);
	public Utilisateur getUtilisateurPseudo(int noUtilisateur);
	public Utilisateur getUtilisateurByPseudo(String pseudo);
}
