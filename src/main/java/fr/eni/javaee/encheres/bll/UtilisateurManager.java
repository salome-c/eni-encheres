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
	private static UtilisateurManager instance;
	
	public static UtilisateurManager getInstance() {
		if (instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}
	
	private UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	public Utilisateur connectUtilisateur(Utilisateur utilisateur) {
		return this.utilisateurDAO.connectUtilisateur(utilisateur);
	}
	
	public String addUtilisateur(Utilisateur utilisateur) {
		String utilisateurNotOk = checkUtilisateur(utilisateur, true, true);
		if (utilisateurNotOk == null) {
			this.utilisateurDAO.addUtilisateur(utilisateur);
		}
		return utilisateurNotOk;
	}
	
	public String updateUtilisateur(Utilisateur newData, boolean checkUniquePseudo, boolean checkUniqueMotDePasse) {
		String utilisateurNotOk = checkUtilisateur(newData, checkUniquePseudo, checkUniqueMotDePasse);
		if (utilisateurNotOk == null) {
			this.utilisateurDAO.updateUtilisateur(newData);
		}
		return utilisateurNotOk;
	}
	
	public void deleteUtilisateur(int noUtilisateur) {
		this.utilisateurDAO.deleteUtilisateur(noUtilisateur);
	}
	
	public Utilisateur getUtilisateurPseudo(int noUtilisateur) {
		return this.utilisateurDAO.getUtilisateurPseudo(noUtilisateur);
	}
	
	public Utilisateur getUtilisateurByPseudo(String pseudo) {
		return this.utilisateurDAO.getUtilisateurByPseudo(pseudo);
	}
	
	public static String encryptPassword(String motDePasse) {
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
	
	public boolean checkPassword(int noUtilisateur, String motDePasse) {
		return this.utilisateurDAO.checkPassword(noUtilisateur, motDePasse);
	}
	
	private String checkUtilisateur(Utilisateur utilisateur, boolean checkExistingPseudo, boolean checkExistingEmail) {
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
				|| !pseudo.matches("^[a-zA-Z0-9]+$") || !codePostal.matches("^[0-9]{5}$") || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
			return "Certains champs ne sont pas renseignés selon le format attendu";
		}
		
		if (checkExistingPseudo) {
			if (this.utilisateurDAO.searchExistingPseudo(pseudo)) {
				return "Ce pseudo est déjà utilisé";
			}
		}
		
		if (checkExistingEmail) {
			if (this.utilisateurDAO.searchExistingEmail(email)) {
				return "Cet email est déjà utilisé";
			}
		}
		
		return null;
	}
}
