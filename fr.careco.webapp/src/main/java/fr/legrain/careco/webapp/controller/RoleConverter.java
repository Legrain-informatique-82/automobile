package fr.legrain.careco.webapp.controller;

import javax.ejb.EJB;
import javax.ejb.FinderException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import fr.legrain.bdg.tiers.service.remote.IRoleServiceRemote;
import fr.legrain.tiers.model.Role;


@FacesConverter(forClass=Role.class, value="aaa")
//http://stackoverflow.com/questions/13156671/how-can-i-inject-in-facesconverter
public class RoleConverter implements Converter {
	
	@EJB IRoleServiceRemote  roleService;

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object valueToRender) {
        // Convert MyObj to its unique String representation.
    	if(valueToRender!=null) {
    		return ((Role) valueToRender).getRole();
    	} else {
    		return "";
    	}
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        // Convert String to MyObj.
    	try {
    		//pas d'injection dans les converter avan jsf 2.3
    		roleService = (IRoleServiceRemote) new InitialContext().lookup("java:global/fr.careco.ear/fr.careco.ejb.mvn/RoleService");
    	
			return roleService.findByCode(submittedValue);
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