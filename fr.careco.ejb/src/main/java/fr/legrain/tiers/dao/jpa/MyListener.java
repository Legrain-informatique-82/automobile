package fr.legrain.tiers.dao.jpa;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import fr.legrain.bdg.tiers.service.local.IUserServiceLocal;
import fr.legrain.bdg.tiers.service.remote.IUserCompanyServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IUserServiceRemote;
import fr.legrain.tiers.service.SessionInfo;
import fr.legrain.tiers.service.UsersService;

/*
 * http://www.objectdb.com/java/jpa/persistence/event
 * 
 * 
 * 
 * http://www.objectdb.com/java/jpa/persistence/event
 * http://stackoverflow.com/questions/552968/can-i-inject-properties-into-a-jpa-entity-listener
 * http://seamframework.org/130341.lace
 * http://stackoverflow.com/questions/12061415/hold-session-in-stateful-ejb-3-1-bean
 * http://stackoverflow.com/questions/8480096/using-a-stateful-session-bean-to-track-an-users-session/8540181#8540181
 * 
 */
public class MyListener {
	
	//private IUserServiceLocal userService = null;
	private SessionInfo sessionInfo = null;
	
	public MyListener() {
		
	}
	
	/*
	java:global/fr.careco.ear/fr.careco.ejb.mvn/UsersService!fr.legrain.bdg.tiers.service.local.IUserServiceLocal
	java:app/fr.careco.ejb.mvn/UsersService!fr.legrain.bdg.tiers.service.local.IUserServiceLocal
	java:module/UsersService!fr.legrain.bdg.tiers.service.local.IUserServiceLocal
	 */
	public void init() {
		try {
			/*
			 * En attendant JPA 2.1, il est impossbile d'utilisé CDI et les injections directement dans les EntityListener
			 * Il faut donc soit utiliser un lookup pour obtenir un EJB mais peu poser des problèmes (car requête, donc nouvelle transcation, donc bdd pas dans le même état)
			 * Soit chercher l'info directement dans un Bean géréré par CDI via l'interface BeanManager
			 * 
			 * Ressources :
			 * http://stackoverflow.com/questions/11986847/java-ee-6-javax-annotation-managedbean-vs-javax-inject-named-vs-javax-faces
			 * http://www.oracle.com/technetwork/articles/java/cdi-javaee-bien-225152.html
			 * http://stackoverflow.com/questions/16814873/lookup-session-scoped-bean-in-cdi-using-beanmanager
			 * http://deltaspike.apache.org/index.html
			 * http://stackoverflow.com/questions/552968/can-i-inject-properties-into-a-jpa-entity-listener
			 * http://stackoverflow.com/questions/10765508/cdi-injection-in-entitylisteners
			 */
//			if(userService==null)
//				userService = (IUserServiceLocal)  new InitialContext().lookup("java:module/UsersService!fr.legrain.bdg.tiers.service.local.IUserServiceLocal");
////			UserData userData = userDataService.getUserData();
////			entity.setAuditUser(userData.getUid());
			
			final BeanManager bm = (BeanManager) InitialContext.doLookup("java:comp/BeanManager");
	        final Iterator<Bean<?>> iter = bm.getBeans(SessionInfo.class).iterator();
	        if (!iter.hasNext()) {
	            throw new IllegalStateException("CDI BeanManager cannot find an instance of requested type " + SessionInfo.class.getName());
	        }
	        final Bean<?> bean = (Bean<?>) iter.next();
	        final CreationalContext<?> ctx = bm.createCreationalContext(bean);
	        sessionInfo = (SessionInfo) bm.getReference(bean, SessionInfo.class, ctx);
	        
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
    @PrePersist void onPrePersist(ILgrEntity o) {
    	init();
    	if(sessionInfo!=null) {
    		o.setQuiCree(sessionInfo.getUser().getId());
    		o.setIp(sessionInfo.getIpAddress());
    	} else {
    		System.out.println("onPrePersist - Impossible d'enregistrer l'utilisateur");
    	}
    	o.setQuanCree(new Date());
    }
    
    //@PostPersist void onPostPersist(ILgrEntity o) {}
    //@PostLoad void onPostLoad(ILgrEntity o) {}
    
    @PreUpdate void onPreUpdate(ILgrEntity o) {
    	init();
    	if(sessionInfo!=null) {
    		o.setQuiModif(sessionInfo.getUser().getId());
    		o.setIp(sessionInfo.getIpAddress());
    	} else {
    		System.out.println("onPreUpdate - Impossible d'enregistrer l'utilisateur");
    	}
    	o.setQuandModif(new Date());
    }
    
   // @PostUpdate void onPostUpdate(ILgrEntity o) {}
   // @PreRemove void onPreRemove(ILgrEntity o) {}
   // @PostRemove void onPostRemove(ILgrEntity o) {}
}

/*
@Entity @EntityListeners({MyListener1.class, MyListener2.class})
public class MyEntityWithTwoListeners {
} 
 */
