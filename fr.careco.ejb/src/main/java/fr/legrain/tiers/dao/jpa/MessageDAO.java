package fr.legrain.tiers.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.legrain.tiers.dao.IMessageDAO;
import fr.legrain.tiers.model.Message;
import fr.legrain.validator.common.BeanValidator;

public class MessageDAO implements IMessageDAO {
	
	static Logger logger = Logger.getLogger(MessageDAO.class);

	@PersistenceContext(unitName = "careco")
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public MessageDAO() {
		// TODO Auto-generated constructor stub
	}

	private String defaultJPQLQuery = "select a from Message a";


	public void persist(Message transientInstance) {
		System.out.println("persisting Message instance");
		try {
			entityManager.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			re.printStackTrace();
			throw re;
		}
	}

	public void remove(Message persistentInstance) {
		System.out.println("removing Message instance");
		try {
			Message e = entityManager.merge(persistentInstance);
			entityManager.remove(e);
			System.out.println("remove successful");
		} catch (RuntimeException re) {
			System.out.println("remove failed");
			re.printStackTrace();
			throw re;
		}
	}

	public Message merge(Message detachedInstance) {
		System.out.println("merging Message instance");
		try {
			Message result = entityManager.merge(detachedInstance);
			//entityManager.flush();
			System.out.println("merge successful");
			return result;
		} catch (RuntimeException re) {
			System.out.println("merge failed");
			re.printStackTrace();
			throw re;
		}
	}

	public Message findById(int id) {
		System.out.println("getting Message instance with id: " + id);
		try {
			Message instance = entityManager.find(Message.class, id);
			System.out.println("get successful");
			return instance;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}

	public Message findByCode(String code) {
		System.out.println("getting Message instance with code: " + code);
		try {
			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from Message f where f.Messagename='"+code+"'");
				Message instance = (Message)query.getSingleResult();
				System.out.println("get successful");
				return instance;
			}
			return null;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<Message> selectMessageFrom(int idUser) {
		try {
			Query query = entityManager.createQuery("select f from Message f where f.de.id = :idUser");
			query.setParameter("idUser", idUser);
			List<Message> l = query.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<Message> selectMessageTo(int idUser) {
		try {
			Query query = entityManager.createQuery("select f from Message f where f.a.id = :idUser");
			query.setParameter("idUser", idUser);
			List<Message> l = query.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public Integer getNbNouveauMessage(int idUser) {
		try {
			Query query = entityManager.createQuery("select f from Message f where f.a.id = :idUser and f.nouveau = 1");
			query.setParameter("idUser", idUser);
			List<Message> l = query.getResultList();
			System.out.println("selectAll successful");
			return l.size();
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}


	public List<Message> selectAll() {
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<Message> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<Message> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<Message> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<Message> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<Message> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(Message value) throws Exception {
		BeanValidator<Message> validator = new BeanValidator<Message>(Message.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(Message value, String propertyName) throws Exception {
		BeanValidator<Message> validator = new BeanValidator<Message>(Message.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(Message transientInstance) {
		entityManager.detach(transientInstance);
	}

}
