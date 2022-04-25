package fr.legrain.tiers.service;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import fr.legrain.tiers.model.User;

@SessionScoped
public class SessionInfo implements Serializable{

	private User user;
	private String ipAddress;

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}
