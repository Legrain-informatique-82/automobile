package fr.legrain.careco.v1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CarModel")
public class CarModel implements java.io.Serializable {

	private static final long serialVersionUID = -6627654627912113877L;

	@Id
	@Column(name="id")
	private Integer id;

	@Column(name="name")
	private String 	name;
	
	@Column(name="match")
	private String 	match;
	
	@Column(name="comments")
	private String 	comments;
	
	@Column(name="carBrandId")
	private Integer carBrandId;
	
	@Column(name="poId")
	private Integer poId;
	
	@Column(name="vovaId")
	private Integer vovaId;
	
	@Column(name="axioId")
	private Integer axioId;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getMatch() {
		return match;
	}

	public String getComments() {
		return comments;
	}

	public Integer getCarBrandId() {
		return carBrandId;
	}

	public Integer getPoId() {
		return poId;
	}

	public Integer getVovaId() {
		return vovaId;
	}

	public Integer getAxioId() {
		return axioId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMatch(String match) {
		this.match = match;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setCarBrandId(Integer carBrandId) {
		this.carBrandId = carBrandId;
	}

	public void setPoId(Integer poId) {
		this.poId = poId;
	}

	public void setVovaId(Integer vovaId) {
		this.vovaId = vovaId;
	}

	public void setAxioId(Integer axioId) {
		this.axioId = axioId;
	}
	

}
