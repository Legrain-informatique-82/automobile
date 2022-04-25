package fr.legrain.careco.v1.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "CoreUser")
public class CoreUser implements java.io.Serializable {


	private static final long serialVersionUID = 1L;


	@Id 
	@Column(name="id")
 	private Integer id;
 	
 	@Column(name="fname")
	private String 	fname;
	
	@Column(name="lname")
	private String	lname;
	
	@Column(name="email")
	private String	email;
	
	@Column(name="password")
	private String	password;
	
	@Column(name="roles")
	private String	roles;
	
	@Column(name="lastLogin")
	@Temporal(TemporalType.TIME)
	private	 Date lastLogin;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	
}
