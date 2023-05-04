package fr.eni.javaee.encheres.bll;

import fr.eni.javaee.encheres.bo.Categorie;
import fr.eni.javaee.encheres.dal.DAOFactory;
import fr.eni.javaee.encheres.dal.ICategorieDAO;

public class CategorieManager {
	private ICategorieDAO categorieDAO;
	private static CategorieManager instance;
	
	public static CategorieManager getInstance() {
		if (instance == null) {
			instance = new CategorieManager();
		}
		return instance;
	}
	
	private CategorieManager() {
		this.categorieDAO = DAOFactory.getCategorieDAO();
	}
	
	public Categorie[] getCategories() {
		return this.categorieDAO.getCategories();
	}
	
	public Categorie getCategorie(int noCategorie) {
		return this.categorieDAO.getCategorie(noCategorie);
	}
}
