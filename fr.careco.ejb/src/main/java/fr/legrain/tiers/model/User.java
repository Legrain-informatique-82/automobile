package fr.legrain.tiers.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import sun.misc.BASE64Encoder;
import fr.legrain.tiers.dao.jpa.ILgrEntity;
import fr.legrain.tiers.dao.jpa.MyListener;

@Entity
@Table(name = "Users")
@EntityListeners({MyListener.class})
@NamedQueries(value = { 
		@NamedQuery(name=User.QN.FIND_BY_USERNAME, query="select f from User f where f.username= :code")
		})
public class User implements java.io.Serializable, ILgrEntity {
	
	public static class QN {
		public static final String FIND_BY_USERNAME = "User.findByUsername";
	}
	
	private static final long serialVersionUID = 1656353212006614210L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="passwd")
	private String passwd;
	
//	@ElementCollection(targetClass=UserRole.class, fetch=FetchType.EAGER)
//	@Enumerated(EnumType.STRING)
//	@CollectionTable(name="UserRoles", joinColumns={@JoinColumn(name="user_id")})
//	@Column(name="role")
	@OneToMany(mappedBy="username", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
	private List<UserRole> roles;
	
	//@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "userCompany")
	private UserCompany userCompany;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="prenom")
	private String prenom;
	
	@Column(name="email")
	private String email;
	
	@Column(name="dernierAcces")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dernierAcces;
	
	@Column(name="quanCree")
	@Temporal(TemporalType.TIMESTAMP)
	private Date quanCree;
	
	@Column(name="quandModif")
	@Temporal(TemporalType.TIMESTAMP)
	private Date quandModif;
	
	@Column(name="quiCree")
	private Integer quiCree;
	
	@Column(name="quiModif")
	private Integer quiModif;
	
	@Column(name="tableOrigine")
	private String tableOrigine;
	
	@Column(name="idOrigine")
	private Integer idOrigine;
	
	@Column(name="ip")
	private String ip;
	
	@Column(name="versionObj")
	private Integer versionObj;
	
	public String passwordHashSHA256_Base64(String originalPassword) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");

			BASE64Encoder enc = new sun.misc.BASE64Encoder();

			byte[] digest = md.digest(originalPassword.getBytes()); // Missing charset
			String base64 = enc.encode(digest);

			return base64;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean hasRole(String role) {
		for (UserRole r : roles) {
			if(r.getUserRoles().getRole().equals(role))
			return true;
		}
        return false;
    }
	
	public boolean isDev() {
    	return isDev(getUsername());
    }
    
    public boolean isDev(String username) {
    	boolean isDev = false;
    	if(username!=null && !username.equals("")) {
    		if(username.equals("nicolas")
    				|| username.equals("ppottier")
    				) {
    			isDev = true;
    		}
    	}
    	return isDev;
    }
    
    public boolean isDevLgr() {
    	return isDevLgr(getUsername());
    }
    
    public boolean isDevLgr(String username) {
    	boolean isDev = false;
    	if(username!=null && !username.equals("")) {
    		if(username.equals("nicolas")
    				) {
    			isDev = true;
    		}
    	}
    	return isDev;
    }
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

	public UserCompany getUserCompany() {
		return userCompany;
	}

	public void setUserCompany(UserCompany userCompany) {
		this.userCompany = userCompany;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDernierAcces() {
		return dernierAcces;
	}

	public void setDernierAcces(Date dernierAcces) {
		this.dernierAcces = dernierAcces;
	}

	public Date getQuanCree() {
		return quanCree;
	}

	public Date getQuandModif() {
		return quandModif;
	}

	public Integer getQuiCree() {
		return quiCree;
	}

	public Integer getQuiModif() {
		return quiModif;
	}

	public String getTableOrigine() {
		return tableOrigine;
	}

	public Integer getIdOrigine() {
		return idOrigine;
	}

	public String getIp() {
		return ip;
	}

	public Integer getVersionObj() {
		return versionObj;
	}

	public void setQuanCree(Date quanCree) {
		this.quanCree = quanCree;
	}

	public void setQuandModif(Date quandModif) {
		this.quandModif = quandModif;
	}

	public void setQuiCree(Integer quiCree) {
		this.quiCree = quiCree;
	}

	public void setQuiModif(Integer quiModif) {
		this.quiModif = quiModif;
	}

	public void setTableOrigine(String tableOrigine) {
		this.tableOrigine = tableOrigine;
	}

	public void setIdOrigine(Integer idOrigine) {
		this.idOrigine = idOrigine;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}
	
	

}
