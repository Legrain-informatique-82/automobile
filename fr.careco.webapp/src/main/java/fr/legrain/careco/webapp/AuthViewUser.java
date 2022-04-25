package fr.legrain.careco.webapp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import fr.legrain.bdg.tiers.service.remote.IAuthURLServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IAuthViewServiceRemote;
import fr.legrain.tiers.model.AuthURL;
import fr.legrain.tiers.model.AuthView;
import fr.legrain.tiers.model.User;
import fr.legrain.tiers.model.UserRole;

@ManagedBean
@ViewScoped
public class AuthViewUser {

	@ManagedProperty("#{auth.user}")
    private User user;
    
	public void setUser(User user) {
		this.user = user;
	}

	
	@EJB
	private IAuthViewServiceRemote authViewService;

    @PostConstruct
    public void init() {
        
    }
    
    public boolean render(String componentID) {
    	System.out.println("componentID : "+componentID);
    	List<AuthView> l = restrictedView();
		return false;
    }
    
    public List<AuthView> restrictedView() {    	
    	List<AuthView> l = new ArrayList<AuthView>();
    	
    	for (UserRole r : user.getRoles()) {
			l.addAll(authViewService.findByRoleID(r.getUserRoles().getId()));
		}
    	
    	return l;
    }

}