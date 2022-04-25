package fr.legrain.careco.webapp.controller;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ejb.FinderException;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import fr.legrain.bdg.tiers.service.remote.IUserCompanyServiceRemote;
import fr.legrain.tiers.model.UserCompany;


@FacesConverter(forClass=UserCompany.class)
//http://stackoverflow.com/questions/13156671/how-can-i-inject-in-facesconverter
//@ManagedBean
//@SessionScoped
public class UserCompanyConverter implements Converter {
	
	@EJB IUserCompanyServiceRemote  userCompanyService;

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object valueToRender) {
        // Convert MyObj to its unique String representation.
    	if(valueToRender!=null) {
    		return ((UserCompany) valueToRender).getNom();
    	} else {
    		return "";
    	}
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        // Convert String to MyObj.
    	try {
    		//pas d'injection dans les converter avan jsf 2.3
    		userCompanyService = (IUserCompanyServiceRemote) new InitialContext().lookup("java:global/fr.careco.ear/fr.careco.ejb.mvn/UserCompanyService");
    	
			return userCompanyService.findByCode(submittedValue);
		} catch (FinderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return submittedValue;
    }

}