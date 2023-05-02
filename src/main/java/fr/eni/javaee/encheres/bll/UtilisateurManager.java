package fr.eni.javaee.encheres.bll;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

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
	
	public String creerUtilisateur(Utilisateur utilisateur) {
		String infoUtilisateurNonValide = validerInfosUtilisateur(utilisateur, true, true);
		if (infoUtilisateurNonValide == null) {
			this.utilisateurDAO.creerUtilisateur(utilisateur);
		}
		return infoUtilisateurNonValide;
	}
	
	public String modifierUtilisateur(Utilisateur nouvellesInfos, boolean verifierPseudoUnique, boolean verifierMotDePasseUnique) {
		String infoModificationNonValide = validerInfosUtilisateur(nouvellesInfos, verifierPseudoUnique, verifierMotDePasseUnique);
		if (infoModificationNonValide == null) {
			this.utilisateurDAO.modifierUtilisateur(nouvellesInfos);
		}
		return infoModificationNonValide;
	}
	
	public void supprimerUtilisateur(int noUtilisateur) {
		this.utilisateurDAO.supprimerUtilisateur(noUtilisateur);
	}
	
	public Utilisateur getUtilisateurPseudo(int noUtilisateur) {
		return this.utilisateurDAO.getUtilisateurPseudo(noUtilisateur);
	}
	
	public Utilisateur getUtilisateurByPseudo(String pseudo) {
		return this.utilisateurDAO.getUtilisateurByPseudo(pseudo);
	}
	
	public static String crypterMotDePasse(String motDePasse) {
		MessageDigest md;
		StringBuffer sb = new StringBuffer();
		
		if (motDePasse != null) {
			try {
				md = MessageDigest.getInstance("SHA-256");
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
	
	public boolean verifierMotDePasse(int noUtilisateur, String motDePasse) {
		return this.utilisateurDAO.verifierMotDePasse(noUtilisateur, motDePasse);
	}
	
	private String validerInfosUtilisateur(Utilisateur utilisateur, boolean verifierPseudoUnique, boolean verifierEmailUnique) {
		String pseudo = utilisateur.getPseudo();
		String nom = utilisateur.getNom();
		String prenom = utilisateur.getPrenom();
		String email = utilisateur.getEmail();
		String telephone = utilisateur.getTelephone();
		String rue = utilisateur.getRue();
		String codePostal = utilisateur.getCodePostal();
		String ville = utilisateur.getCodePostal();
		String motDePasse = utilisateur.getMotDePasse();
		
		ArrayList<String> mandatoryData = new ArrayList<String>();
		mandatoryData.addAll(Arrays.asList(pseudo, nom, prenom, email, rue, codePostal, ville, motDePasse));
		
		for (int i = 0; i < mandatoryData.size(); i++) {
			if (mandatoryData.get(i) == null || mandatoryData.get(i) == "") {
				return "Tous les champs obligatoires ne sont pas renseignés";
			}
		}
		
		if ((telephone != null && telephone != "" && !telephone.matches("^0[1-9]{1}[0-9]{8}$"))
				|| !pseudo.matches("^[a-zA-Z0-9]+$") || !codePostal.matches("^[0-9]{1}[1-9]{1}[0-9]{3}$") || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
			return "Certains champs ne sont pas renseignés selon le format attendu";
		}
		
		if (verifierPseudoUnique) {
			if (this.utilisateurDAO.rechercherPseudoExistant(pseudo)) {
				return "Ce pseudo est déjà utilisé";
			}
		}
		
		if (verifierEmailUnique) {
			if (this.utilisateurDAO.rechercherEmailExistant(email)) {
				return "Cet email est déjà utilisé";
			}
		}
		
		return null;
	}
}
