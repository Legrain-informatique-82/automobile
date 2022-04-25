package fr.legrain.tiers.dto;

import fr.legrain.bdg.model.ModelObject;

public class TransactionAchatVenteDTO  extends ModelObject implements java.io.Serializable {

	private static final long serialVersionUID = 4328964283266486453L;

	private Integer id;
	
	private String etatVendeur;
	
	private String etatAcheteur;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEtatVendeur() {
		return etatVendeur;
	}

	public void setEtatVendeur(String etatVendeur) {
		this.etatVendeur = etatVendeur;
	}

	public String getEtatAcheteur() {
		return etatAcheteur;
	}

	public void setEtatAcheteur(String etatAcheteur) {
		this.etatAcheteur = etatAcheteur;
	}
	
}
