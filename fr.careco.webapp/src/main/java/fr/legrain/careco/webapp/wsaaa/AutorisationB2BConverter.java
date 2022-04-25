package fr.legrain.careco.webapp.wsaaa;

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

import fr.careco.blueway.ws.model.AutorisationB2B;
import fr.careco.blueway.ws.model.IAutorisationServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IUserCompanyServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IUserServiceRemote;
import fr.legrain.tiers.model.User;
import fr.legrain.tiers.model.UserCompany;


@FacesConverter(forClass=AutorisationB2B.class)
//http://stackoverflow.com/questions/13156671/how-can-i-inject-in-facesconverter
//@ManagedBean
//@SessionScoped
public class AutorisationB2BConverter implements Converter {
	
	@EJB IAutorisationServiceRemote  userService;

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object valueToRender) {
    	if (valueToRender == null || valueToRender.equals("")) {  
            return "";  
        } else {  
            //return String.valueOf(((User) valueToRender).getNom())+" "+String.valueOf(((User) valueToRender).getPrenom());  
        	return String.valueOf(((AutorisationB2B) valueToRender).getCodeAuthen());
        }  
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        // Convert String to MyObj.
    	if (submittedValue.trim().equals("")) {  
    		return null;  
    	} else {  
    		try {  
    			int number = Integer.parseInt(submittedValue);  

    			//pas d'injection dans les converter avant jsf 2.3
    			userService = (IAutorisationServiceRemote) new InitialContext().lookup("java:global/fr.careco.ear/fr.careco.ejb.mvn/AutorisationService!fr.careco.blueway.ws.model.IAutorisationServiceRemote");

    			//return userService.findByCode(submittedValue);
    			return userService.findById(number);

    		} catch (FinderException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (NamingException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
		return submittedValue;
    }

}