package fr.legrain.tiers.dto;

import fr.legrain.bdg.model.ModelObject;

public class AuthViewDTO  extends ModelObject implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9053020118612592500L;
	
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
