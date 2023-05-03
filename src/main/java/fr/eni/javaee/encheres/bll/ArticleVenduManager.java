package fr.eni.javaee.encheres.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.Categorie;
import fr.eni.javaee.encheres.dal.DAOFactory;
import fr.eni.javaee.encheres.dal.IArticleVenduDAO;

public class ArticleVenduManager {
	private IArticleVenduDAO articleVenduDAO;
	private static ArticleVenduManager instance;
	
	public static ArticleVenduManager getInstance() {
		if (instance == null) {
			instance = new ArticleVenduManager();
		}
		return instance;
	}
	
	private ArticleVenduManager() {
		this.articleVenduDAO = DAOFactory.getArticleVenduDAO();
	}
	
	public ArticleVendu[] getArticlesVendus() {
		return articleVenduDAO.getArticlesVendus();
	}
	
	public int creerVente(ArticleVendu article) {
		return articleVenduDAO.creerVente(article);
	}
	
	public String validerVente(ArticleVendu vente) {
		String nom = vente.getNomArticle();
		String description = vente.getDescription();
		String dateDebutEncheres = vente.getDateDebutEncheres();
		String dateFinEncheres = vente.getDateFinEncheres();
		int prix = vente.getMiseAPrix();
		int noUtilisateur = vente.getNoUtilisateur();
		int noCategorie = vente.getNoCategorie();
		
		ArrayList<Object> mandatoryData = new ArrayList<>();
		mandatoryData.addAll(Arrays.asList(nom, description, dateDebutEncheres, dateFinEncheres, prix, noUtilisateur, noCategorie));
		
		for (int i = 0; i < mandatoryData.size(); i++) {
			if (mandatoryData.get(i) == null || mandatoryData.get(i) == "") {
				return "Tous les champs obligatoires ne sont pas renseignés";
			}
		}
		
		Categorie[] categories = CategorieManager.getInstance().getCategories();
		boolean categorieValide = false;
		
		for (int i = 0; i < categories.length && !categorieValide; i++) {
			if (categories[i].getNoCategorie() == noCategorie) {
				categorieValide = true;
			}
		}
		
		if (!categorieValide) {
			return "La catégorie renseignée n'est pas valide";
		}
		
		if (prix < 0) {
			return "La mise à prix ne peut pas à être inférieure à 0";
		}
		
		if (LocalDate.parse(dateDebutEncheres).isAfter(LocalDate.parse(dateFinEncheres))) {
			return "La date de fin d'enchère ne peut pas être antérieure à la date de début d'enchère";
		}
		
		return null;
	}

}
