package fr.legrain.tiers.dto;

import fr.legrain.bdg.model.ModelObject;

public class GroupeEntrepriseDTO  extends ModelObject implements java.io.Serializable {

	private static final long serialVersionUID = 4329013592361147062L;
	
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
}
