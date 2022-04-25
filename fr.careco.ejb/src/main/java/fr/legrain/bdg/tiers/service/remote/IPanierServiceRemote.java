package fr.legrain.bdg.tiers.service.remote;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

import fr.legrain.tiers.dto.PanierDTO;
import fr.legrain.tiers.model.GarantieCareco;
import fr.legrain.tiers.model.Panier;
import fr.legrain.tiers.model.Stock;
import fr.legrain.tiers.model.User;

@Remote

public interface IPanierServiceRemote extends IGenericCRUDServiceRemote<Panier,PanierDTO>,
														IAbstractLgrDAOServer<Panier>,IAbstractLgrDAOServerDTO<PanierDTO>{
	
	public void ajouterPanier(User u,String idPiece);
	
	public List<Panier> findPanierActif(User u,boolean b2c);
	public List<Panier> findPanierPerime(User u,boolean b2c);
	public List<Panier> findPanierActifOuPerime(User u, boolean b2c);
	public List<Panier> findPanierEnCours(User u,boolean b2c);
	public List<Panier> findPanierValider(User u,boolean b2c);
	public List<Panier> findPanierHistorique(User u,boolean b2c);
	
	public GarantieCareco findGarantieCarecoByMontant(BigDecimal prix, Integer duree);
	
	public List<Panier> findPanierContientPiece(Stock piece);
		
}
