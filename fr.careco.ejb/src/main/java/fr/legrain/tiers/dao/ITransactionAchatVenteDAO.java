package fr.legrain.tiers.dao;

import java.util.List;

import fr.legrain.tiers.model.LignePanier;
import fr.legrain.tiers.model.TransactionAchatVente;
//import javax.ejb.Remote;
import fr.legrain.tiers.model.User;

//@Remote
public interface ITransactionAchatVenteDAO extends IGenericDAO<TransactionAchatVente> {
	
	public List<LignePanier> findUserAchatEnCours(User user);
	public List<LignePanier> findUserAchatHistorique(User user);
	
	public List<LignePanier> findUserVentesEnCours(User user);
	public List<LignePanier> findUserVentesLocaleEnCours(User user);
	public List<LignePanier> findUserVentesValider(User user);
	public List<LignePanier> findUserVentesHistorique(User user);
	public List<LignePanier> findUserVentesLocaleHistorique(User user);

}
