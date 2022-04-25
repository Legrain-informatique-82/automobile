package fr.legrain.careco.webapp;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.FinderException;
//import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import fr.legrain.bdg.tiers.service.remote.IAuthURLServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IUserCompanyServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IUserServiceRemote;
import fr.legrain.careco.aaa.model.VehiculeCacheAAA;
import fr.legrain.tiers.model.AuthURL;
import fr.legrain.tiers.model.SocietesAmies;
import fr.legrain.tiers.model.User;
import fr.legrain.tiers.model.UserCompany;
import fr.legrain.tiers.model.UserRole;
import fr.legrain.tiers.service.SessionInfo;
import fr.legrain.tiers.service.UsersService;

@ManagedBean
//@ViewScoped
@SessionScoped
public class Auth {

    private String username;
    private String password;
    private String originalURL;
    private User user;
    private String loginMaint;
    
//    public User getUser() {
//		return user;
//	}
    @Inject SessionInfo sessionInfo;

	@EJB
    private IUserServiceRemote userService;
	
	@EJB
    private IUserCompanyServiceRemote userCompanyService;
	
	@EJB
	private IAuthURLServiceRemote authURLService;

    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        originalURL = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);

        if (originalURL == null) {
            originalURL = externalContext.getRequestContextPath() + "/home.xhtml";
        } else {
            String originalQuery = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);

            if (originalQuery != null) {
            	originalURL += "?" + originalQuery;
            }
        }
    }
    
    public boolean hasRole(String role) {
//    	FacesContext context = FacesContext.getCurrentInstance();
//    	ExternalContext externalContext = context.getExternalContext();
//    	User user = (User) externalContext.getSessionMap().get("userSession");
    	return user.hasRole(role);
    }
    
    public boolean isDev() {
    	return user.isDev();
    }
    
    public boolean isDev(String username) {
    	return user.isDev(username);
    }
    
    public boolean isDevLgr() {
    	return user.isDevLgr();
    }
    
    public boolean isDevLgr(String username) {
    	return user.isDevLgr(username);
    }
    
    public List<AuthURL> restrictedURL() {
//    	FacesContext context = FacesContext.getCurrentInstance();
//    	ExternalContext externalContext = context.getExternalContext();
//    	User user = (User) externalContext.getSessionMap().get("userSession");
    	
    	List<AuthURL> l = new ArrayList<AuthURL>();
    	
    	for (UserRole r : user.getRoles()) {
			
			l.addAll(authURLService.findByRoleID(r.getUserRoles().getId()));
		}
    	
    	return l;
    }


    public String login() throws IOException {
    	
    	FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            //log4jLogger.info("LOGIN");
        	
//            request.login(username, password);  
//            user = userService.findByCode(username);
        	
        	//http://stackoverflow.com/questions/6589138/using-http-request-login-with-jboss-jaas
        	Principal userPrincipal = request.getUserPrincipal();
            if (request.getUserPrincipal() != null) {
                request.logout();
            }
            
            boolean siteEnMaintenance = false;
            if(ConstWeb.maintenance && !isDev(username)) {
            	siteEnMaintenance = true;
            }
            
            if(!siteEnMaintenance) {
	            request.login(username, password);
	            userPrincipal = request.getUserPrincipal();
	            user = userService.findByCode(userPrincipal.getName());
	            
	            sessionInfo.setUser(user);
	            sessionInfo.setIpAddress(findUserIPAddress());
	            
	            System.out.println("User "+user.getUsername()+" *** "+user.getPasswd()+" successfully logged in...");
	            System.out.println("User "+user.getUsername()+" ("+user.getNom()+" "+user.getPrenom()+") "+user.getUserCompany().getNom()+" ** IP : "+findUserIPAddress());
	            user.setDernierAcces(new Date());
	            
	            userService.enregistrerMerge(user);
	            
	            for (UserRole r : user.getRoles()) {
					System.out.println(r.getUserRoles().getRole());
				}
	            
	            //if(user.getUserCompany().getMultiSite()!=null) {
	            if(user.getUserCompany().getIdAdherent()!=null) {
	            	System.out.println("Multi site : ");
	            	//for (UserCompany uc : user.getUserCompany().getMultiSite()) {
	            	for (UserCompany uc : user.getUserCompany().getIdAdherent().getMultiSite()) {
						System.out.println(uc.getNom());
					}
	            }
	            
	//            UserCompany uc = userCompanyService.chargeSocietesAmie(user.getUserCompany());
	//            if(uc.getAmies()!=null) {
	//            	System.out.println("Amies : ");
	//            	for (SocietesAmies a : uc.getAmies()) {
	//					System.out.println(a.getSocieteB().getNom());
	//				}
	//            }
	            
	            if(user.getUserCompany().getAmies()!=null) {
	            	System.out.println("Amies : ");
	            	for (SocietesAmies uc : user.getUserCompany().getAmies()) {
						System.out.println(uc.getSocieteB().getNom());
					}
	            }
	            
	            externalContext.getSessionMap().put("userSession", user);
	            
	            if(ConstWeb.LOGIN_AAA.equals(user.getUsername())) {
	            	return ConstWeb.REPERTOIRE_LOGIN_AAA+"/index.xhtml";
	            }
            } else {
            	 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                         FacesMessage.SEVERITY_WARN, "Site en cours de maintenance ...",""));
            	 return "/login/error.xhtml";
            }
            
//          externalContext.redirect(originalURL);
        } catch (ServletException e) {
        	e.printStackTrace();
            //log4jLogger.info("DENIED");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_FATAL, "Utilisateur ou mot de passe incorrects"/*e.getClass().getName()*/, e.getMessage()));
            return "/login/error.xhtml";
        } catch (FinderException e) {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_FATAL, e.getClass().getName(), e.getMessage()));
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "/accueil_careco_template.xhtml";
        
       
    }
    
    public String findUserIPAddress() {
	    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    String ipAddress = request.getHeader("X-FORWARDED-FOR");
	    if (ipAddress == null) {
	        ipAddress = request.getRemoteAddr();
	    }
	    System.out.println("ipAddress:" + ipAddress);
	    return ipAddress;
    }

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void logout(ActionEvent a) throws IOException {
		logout();
//	//public void logout() throws IOException {
//	//public String logout() throws IOException {
//        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//        externalContext.invalidateSession();
//        externalContext.redirect(externalContext.getRequestContextPath() + "/login/login.xhtml");
//        //return "/login/login.xhtml";
//       // response.sendRedirect(request.getContextPath() + "/login.xhtml");
//        //return "/login/login.xhtml?faces-redirect=true";
    }
	
	public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        System.out.println("Logout : User "+user.getUsername()+" ("+user.getNom()+" "+user.getPrenom()+") "+user.getUserCompany().getNom()+" ** IP : "+findUserIPAddress());
        externalContext.redirect(externalContext.getRequestContextPath() + "/login/login.xhtml");
    }
	
	static public User findUserInSession() {
		FacesContext context = FacesContext.getCurrentInstance();
		return (User) context.getApplication().evaluateExpressionGet(context,"#{auth.user}", User.class);
	}

	public String getLoginMaint() {
		return loginMaint;
	}

	public void setLoginMaint(String loginMaint) {
		this.loginMaint = loginMaint;
	}

    // Getters/setters for username and password.
}