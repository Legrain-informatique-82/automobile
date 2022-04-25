package fr.legrain.careco.v1.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class UserV1 implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	
//	@Id
//	@Column(name="id")
// 	private Integer id;
	
	@Id
	//@JoinColumn(name = "parentCoreUserId")
	//@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
 	//private CoreUser parentCoreUserId;
	private Integer parentCoreUserId; 
	//erreur sur certaine clé car pas de foreign key dans la base donc les id n'exsite pas forcément, 
	//donc Integer pour ne pas planter
	
	@Column(name="directLine")
 	private Integer directLine;
	
	@Column(name="mobile")
 	private Integer mobile;
	
	@Column(name="titleId")
 	private Integer titleId;
	
	@JoinColumn(name = "companyId")
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
 	private Company companyId;

	public Integer getParentCoreUserId() {
		return parentCoreUserId;
	}

	public Integer getDirectLine() {
		return directLine;
	}

	public Integer getMobile() {
		return mobile;
	}

	public Integer getTitleId() {
		return titleId;
	}

	public Company getCompanyId() {
		return companyId;
	}

	public void setParentCoreUserId(Integer parentCoreUserId) {
		this.parentCoreUserId = parentCoreUserId;
	}

	public void setDirectLine(Integer directLine) {
		this.directLine = directLine;
	}

	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	public void setCompanyId(Company companyId) {
		this.companyId = companyId;
	}
	
	
}
