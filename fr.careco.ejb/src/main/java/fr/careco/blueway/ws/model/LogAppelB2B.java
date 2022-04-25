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
@Table(name = "Log_Appel_B2B")
@NamedQueries(value = { 
		@NamedQuery(name=LogAppelB2B.QN.FIND_BY_LOGIN, query="select f from LogAppelB2B f where f.Login_Log = :login"),
		@NamedQuery(name=LogAppelB2B.QN.FIND_BY_DATE, query="select f from LogAppelB2B f where f.Date_Log between :debut and :fin"),
		@NamedQuery(name=LogAppelB2B.QN.FIND_BY_LOGIN_DATE, query="select f from LogAppelB2B f where f.Login_Log = :login and f.Date_Log between :debut and :fin")
		})
public class LogAppelB2B implements java.io.Serializable {
				
	public static class QN {
		public static final String FIND_BY_LOGIN = "LogAppelB2B.findByLogin";
		public static final String FIND_BY_DATE = "LogAppelB2B.findByDate";
		public static final String FIND_BY_LOGIN_DATE = "LogAppelB2B.findByLoginDate";
	}
		
	private static final long serialVersionUID = -5737027378951831871L;
	
	/*
	CREATE TABLE `Log_Appel_B2B` (
			  `Code_Log` int(11) NOT NULL AUTO_INCREMENT,
			  `Login_Log` varchar(50) DEFAULT NULL,
			  `Mdp_Log` varchar(50) DEFAULT NULL,
			  `Immat_Log` varchar(20) DEFAULT NULL,
			  `TyperReq_Log` int(5) DEFAULT NULL,
			  `Date_Log` date DEFAULT NULL,
			  `Type_Log` varchar(50) DEFAULT NULL,
			  `Nom_Log` varchar(3) DEFAULT NULL,
			  `Heure_Log` time DEFAULT NULL,
			  `CodeType_Log` int(2) DEFAULT NULL,
			  `TypeConnex` varchar(7) DEFAULT NULL,
			  PRIMARY KEY (`Code_Log`),
			  UNIQUE KEY `Code_Log` (`Code_Log`)
			) ENGINE=InnoDB;
			
			ALTER TABLE `Log_Appel_B2B` ADD `Message` TEXT NULL ,
ADD `IP` VARCHAR( 256 ) NULL ,
ADD `Logiciel_Client` VARCHAR( 256 ) NULL ;

    */

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Code_Log")
	private Integer Code_Log;
	
	@Column(name="Login_Log")
	private String Login_Log;
	
	@Column(name="Mdp_Log")
	private String Mdp_Log;
	
	@Column(name="Immat_Log")
	private String Immat_Log;
	
	@Column(name="TyperReq_Log")
	private Integer TyperReq_Log;
	
	@Column(name="Date_Log")
	@Temporal(TemporalType.DATE)
	private Date Date_Log;
	
	@Column(name="Type_Log")
	private String Type_Log;
	
	@Column(name="Heure_Log")
	@Temporal(TemporalType.TIMESTAMP)
	private Date Heure_Log;
	
	@Column(name="Nom_Log")
	private String Nom_Log;
	
	@Column(name="CodeType_Log")
	private Integer CodeType_Log;
	
	@Column(name="TypeConnex")
	private String TypeConnex;
	
	@Column(name="Message")
	private String message;
	
	@Column(name="IP")
	private String ip;
	
	@Column(name="Logiciel_Client")
	private String logicielClient;

	public Integer getCode_Log() {
		return Code_Log;
	}

	public String getLogin_Log() {
		return Login_Log;
	}

	public String getMdp_Log() {
		return Mdp_Log;
	}

	public String getImmat_Log() {
		return Immat_Log;
	}

	public Integer getTyperReq_Log() {
		return TyperReq_Log;
	}

	public Date getDate_Log() {
		return Date_Log;
	}

	public String getType_Log() {
		return Type_Log;
	}

	public Date getHeure_Log() {
		return Heure_Log;
	}

	public String getNom_Log() {
		return Nom_Log;
	}

	public Integer getCodeType_Log() {
		return CodeType_Log;
	}

	public String getTypeConnex() {
		return TypeConnex;
	}

	public void setCode_Log(Integer code_Log) {
		Code_Log = code_Log;
	}

	public void setLogin_Log(String login_Log) {
		Login_Log = login_Log;
	}

	public void setMdp_Log(String mdp_Log) {
		Mdp_Log = mdp_Log;
	}

	public void setImmat_Log(String immat_Log) {
		Immat_Log = immat_Log;
	}

	public void setTyperReq_Log(Integer typerReq_Log) {
		TyperReq_Log = typerReq_Log;
	}

	public void setDate_Log(Date date_Log) {
		Date_Log = date_Log;
	}

	public void setType_Log(String type_Log) {
		Type_Log = type_Log;
	}

	public void setHeure_Log(Date heure_Log) {
		Heure_Log = heure_Log;
	}

	public void setNom_Log(String nom_Log) {
		Nom_Log = nom_Log;
	}

	public void setCodeType_Log(Integer codeType_Log) {
		CodeType_Log = codeType_Log;
	}

	public void setTypeConnex(String typeConnex) {
		TypeConnex = typeConnex;
	}

	public String getMessage() {
		return message;
	}

	public String getIp() {
		return ip;
	}

	public String getLogicielClient() {
		return logicielClient;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setLogicielClient(String logicielClient) {
		this.logicielClient = logicielClient;
	}

	
}
