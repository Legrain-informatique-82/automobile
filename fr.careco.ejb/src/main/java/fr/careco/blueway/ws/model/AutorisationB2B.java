package fr.careco.blueway.ws.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "AutorisationB2B")
@NamedQueries(value = { 
		@NamedQuery(name=AutorisationB2B.QN.FIND_BY_LOGIN, query="select f from AutorisationB2B f where f.loginAuthen = :login and f.mdpAuthen = :pwd")
		})
public class AutorisationB2B implements java.io.Serializable {
		
	public static class QN {
		public static final String FIND_BY_LOGIN = "AutorisationB2B.findByLogin";
	}

	private static final long serialVersionUID = 8703730064463172682L;
		
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Code_Authen")
	private Integer codeAuthen;

	@Column(name="Nom_Authen")	
	private String nomAuthen;
	
	@Column(name="Login_Authen")
	private String loginAuthen;
	
	@Column(name="Mdp_Authen")
	private String mdpAuthen;
	
	@Column(name="Nb_Appel_Min")
	private Integer nbAppelMin;
	
	@Column(name="Time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date time;
	
	@Column(name="Activer")
	private Integer activer;

	public Integer getCodeAuthen() {
		return codeAuthen;
	}

	public String getNomAuthen() {
		return nomAuthen;
	}

	public String getLoginAuthen() {
		return loginAuthen;
	}

	public String getMdpAuthen() {
		return mdpAuthen;
	}

	public Integer getNbAppelMin() {
		return nbAppelMin;
	}

	public Date getTime() {
		return time;
	}

	public Integer getActiver() {
		return activer;
	}

	public void setCodeAuthen(Integer codeAuthen) {
		this.codeAuthen = codeAuthen;
	}

	public void setNomAuthen(String nomAuthen) {
		this.nomAuthen = nomAuthen;
	}

	public void setLoginAuthen(String loginAuthen) {
		this.loginAuthen = loginAuthen;
	}

	public void setMdpAuthen(String mdpAuthen) {
		this.mdpAuthen = mdpAuthen;
	}

	public void setNbAppelMin(Integer nbAppelMin) {
		this.nbAppelMin = nbAppelMin;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void setActiver(Integer activer) {
		this.activer = activer;
	}

}
