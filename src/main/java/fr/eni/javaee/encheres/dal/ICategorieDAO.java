package fr.eni.javaee.encheres.dal;

import fr.eni.javaee.encheres.bo.Categorie;

public interface ICategorieDAO {
	public Categorie[] getCategories();
	public Categorie getCategorie(int noCategorie);
}
