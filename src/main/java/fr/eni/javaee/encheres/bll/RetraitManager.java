package fr.eni.javaee.encheres.bll;

import java.util.ArrayList;
import java.util.Arrays;

import fr.eni.javaee.encheres.bo.Retrait;
import fr.eni.javaee.encheres.dal.DAOFactory;
import fr.eni.javaee.encheres.dal.IRetraitDAO;

public class RetraitManager {
	private IRetraitDAO retraitDAO;
	private static RetraitManager instance;
	
	public static RetraitManager getInstance() {
		if (instance == null) {
			instance = new RetraitManager();
		}
		return instance;
	}
	
	private RetraitManager() {
		this.retraitDAO = DAOFactory.getRetraitDAO();
	}
	
	public void creerRetrait(Retrait retrait) {
		this.retraitDAO.creerRetrait(retrait);
	}
	
	public Retrait getRetrait(int noArticle) {
		return this.retraitDAO.getRetrait(noArticle);
	}
	
	public String validerRetrait(Retrait retrait) {
		String rue = retrait.getRue();
		String codePostal = retrait.getCodePostal();
		String ville = retrait.getVille();
		
		ArrayList<String> mandatoryData = new ArrayList<String>();
		mandatoryData.addAll(Arrays.asList(rue, codePostal, ville));
		
		for (int i = 0; i < mandatoryData.size(); i++) {
			if (mandatoryData.get(i) == null || mandatoryData.get(i) == "") {
				return "Tous les champs obligatoires ne sont pas renseignés";
			}
		}
		
		if (!codePostal.matches("^[0-9]{5}$")) {
			return "Le code postal n'est pas renseigné selon le format attendu";
		}
		
		return null;
	}
}
