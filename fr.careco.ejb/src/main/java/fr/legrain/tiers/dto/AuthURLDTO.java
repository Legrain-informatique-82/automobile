package fr.legrain.tiers.dto;

import fr.legrain.bdg.model.ModelObject;

public class AuthURLDTO  extends ModelObject implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3306930216238171689L;

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
