package fr.legrain.tiers.dto;

import fr.legrain.bdg.model.ModelObject;

public class RoleDTO  extends ModelObject implements java.io.Serializable {

	private static final long serialVersionUID = 1408240690913871182L;
	
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
