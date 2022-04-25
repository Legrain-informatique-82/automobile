package fr.legrain.careco.webapp.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="rechercheVehiculePanier")
@SessionScoped
public class RechercheVehiculeSession extends RechercheVehicule {
	//idem RechercheVehicule mais @SessionScoped et non @ViewScoped

}
