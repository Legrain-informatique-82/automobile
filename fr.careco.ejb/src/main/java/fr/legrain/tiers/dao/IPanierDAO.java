package fr.legrain.tiers.dao;

import java.util.List;

import fr.legrain.tiers.model.Panier;
import fr.legrain.tiers.model.Stock;
import fr.legrain.tiers.model.User;

public interface IPanierDAO extends IGenericDAO<Panier> {
	
	public List<Panier> findPanierActif(User u,boolean b2c);
	public List<Panier> findPanierPerime(User u,boolean b2c);
	public List<Panier> findPanierActifOuPerime(User u, boolean b2c);
	public List<Panier> findPanierEnCours(User u,boolean b2c);
	public List<Panier> findPanierValider(User u,boolean b2c);
	public List<Panier> findPanierHistorique(User u,boolean b2c);
	
	public List<Panier> findPanierContientPiece(Stock piece);
}
