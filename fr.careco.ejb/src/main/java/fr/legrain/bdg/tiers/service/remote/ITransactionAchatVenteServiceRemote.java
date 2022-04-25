package fr.legrain.bdg.tiers.service.remote;

import java.util.List;

import javax.ejb.Remote;

import fr.legrain.tiers.dto.TransactionAchatVenteDTO;
import fr.legrain.tiers.model.LignePanier;
import fr.legrain.tiers.model.TransactionAchatVente;
import fr.legrain.tiers.model.User;

@Remote

public interface ITransactionAchatVenteServiceRemote extends IGenericCRUDServiceRemote<TransactionAchatVente,TransactionAchatVenteDTO>,
														IAbstractLgrDAOServer<TransactionAchatVente>,IAbstractLgrDAOServerDTO<TransactionAchatVenteDTO>{
	
	static final public String etatParDefautVendeur = "nouveau";
	static final public String etatParDefautAcheteur = "nouveau";
	
	static final public String ACHETEUR_ARRIVE_PIECE_CONFIRMEE = "arrive confirme";
	static final public String VENDEUR_ARRIVE_PIECE_CONFIRMEE_PAR_ACHETEUR = "arrive confirme par l'acheteur";
	
	static final public String VENDEUR_VENTE_CONFIRMEE = "vente confirmée";
	static final public String ACHETEUR_VENTE_CONFIRMEE_PAR_VENDEUR = "vente confirmée par le vendeur";
	
	static final public String VENDEUR_VENTE_REFUSEE = "vente refusée";
	static final public String ACHETEUR_VENTE_REFUSEE_PAR_VENDEUR = "vente refusée par le vendeur";
	
	static final public String VENDEUR_PIECE_ENVOYEE = "pièce envoyée";
	static final public String ACHETEUR_PIECE_ENVOYEE_PAR_VENDEUR = "pièce envoyée par le vendeur";
	
	static final public String TRANSACTION_LOCALE = "local"; //pour compatibilité pour les transactions avant création de (nouveau,confirmee et annulée) pour les ventes locales
	static final public String TRANSACTION_LOCALE_NOUVEAU = "vente locale";
	static final public String TRANSACTION_LOCALE_CONFIRMEE = "vente locale confirmée";
	static final public String TRANSACTION_LOCALE_ANNULEE = "vente locale annulée";
	
	public List<LignePanier> findUserAchatEnCours(User user);
	public List<LignePanier> findUserAchatHistorique(User user);
	
	public List<LignePanier> findUserVentesEnCours(User user);
	public List<LignePanier> findUserVentesLocaleEnCours(User user);
	public List<LignePanier> findUserVentesValider(User user);
	public List<LignePanier> findUserVentesHistorique(User user);
	public List<LignePanier> findUserVentesLocaleHistorique(User user);
	
}
