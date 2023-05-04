package fr.eni.javaee.encheres.dal;

import fr.eni.javaee.encheres.bo.Retrait;

public interface IRetraitDAO {
	public void addRetrait(Retrait retrait);
	public Retrait getRetrait(int noArticle);
}
