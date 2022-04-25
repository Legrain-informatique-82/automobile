package fr.careco.blueway.ws.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.persistence.NoResultException;

import org.apache.log4j.Logger;

import fr.careco.aaa.ws.BaseDeDonneeInaccessible_Exception;
import fr.careco.aaa.ws.NumeroImmatriculationInconnu_Exception;
import fr.careco.aaa.ws.Quota_Exception;
import fr.careco.aaa.ws.Vehicule;
import fr.careco.aaa.ws.WSSiVinConsulter;
import fr.careco.aaa.ws.WSSiVinConsulterPortType;
import fr.careco.aaa.ws.clientsample.HeaderHandlerResolver;
import fr.legrain.data.AbstractApplicationDAOServer;


@Stateless
//@DeclareRoles("admin")
public class LogAppelService extends AbstractApplicationDAOServer<LogAppelB2B, LogAppelB2BDTO> implements ILogAppelServiceRemote {

	static Logger logger = Logger.getLogger(LogAppelService.class);

	@Inject private ILogAppelB2BDAO daoB2B;
	@Inject private ILogAppelB2CDAO daoB2C;

	/**
	 * Default constructor. 
	 */
	public LogAppelService() {

	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public List<LogAppelB2B> findByLoginB2B(String login) {
		return daoB2B.findByLogin(login);
	}
	
	public List<LogAppelB2B> findByDateB2B(Date debut, Date fin) {
		return daoB2B.findByDate(debut,fin);
	}
	
	public List<LogAppelB2B> findByLoginDateB2B(String login, Date debut, Date fin) {
		return daoB2B.findByLoginDate(login,debut,fin);
	}
	
	public List<LogAppelB2C> findByLoginB2C(String login) {
		return daoB2C.findByLogin(login);
	}
	
	public List<LogAppelB2C> findByDateB2C(Date debut, Date fin) {
		return daoB2C.findByDate(debut,fin);
	}
	
	public List<LogAppelB2C> findByLoginDateB2C(String login, Date debut, Date fin) {
		return daoB2C.findByLoginDate(login,debut,fin);
	}
	
	public LogAppelB2B enregistreLogB2B(LogAppelB2B log) {
		return daoB2B.merge(log);
	}
	
	public LogAppelB2C enregistreLogB2C(LogAppelB2C log) {
		return daoB2C.merge(log);
	}
	
	public List<LogAppelB2C> selectAllB2C() {
		return daoB2C.selectAll();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										ENTITY
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(LogAppelB2B transientInstance) throws CreateException {

		validateEntity(transientInstance);

		daoB2B.persist(transientInstance);
	}

	public void remove(LogAppelB2B persistentInstance) throws RemoveException {
		daoB2B.remove(persistentInstance);
	}

	@Override
	public LogAppelB2B merge(LogAppelB2B detachedInstance) {
		validateEntity(detachedInstance);

		return daoB2B.merge(detachedInstance);
	}

	public LogAppelB2B findById(int id) throws FinderException {
		return daoB2B.findById(id);
	}

	public LogAppelB2B findByCode(String code) throws FinderException {
		return daoB2B.findByCode(code);
	}

	@RolesAllowed("admin")
	public List<LogAppelB2B> selectAll() {
		return daoB2B.selectAll();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										DTO
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<LogAppelB2BDTO> findWithNamedQueryDTO(String queryName) throws FinderException {
		return null;
	}

	@Override
	public List<LogAppelB2BDTO> findWithJPQLQueryDTO(String JPQLquery) throws FinderException {
		List<LogAppelB2B> entityList = daoB2B.findWithJPQLQuery(JPQLquery);
		List<LogAppelB2BDTO> l = null;
		if(entityList!=null) {
			l = listEntityToListDTO(entityList);
		}
		return l;
	}

	public LogAppelB2BDTO entityToDTO(LogAppelB2B entity) {
		//		LogAppelB2BDTO dto = new LogAppelB2BDTO();
		//		dto.setId(entity.getIdTCivilite());
		//		dto.setCodeTCivilite(entity.getCodeTCivilite());
		//		return dto;
		LogAppelB2BMapper mapper = new LogAppelB2BMapper();
		return mapper.mapEntityToDto(entity, null);
	}

	public List<LogAppelB2BDTO> listEntityToListDTO(List<LogAppelB2B> entity) {
		List<LogAppelB2BDTO> l = new ArrayList<LogAppelB2BDTO>();

		for (LogAppelB2B LogAppelB2B : entity) {
			l.add(entityToDTO(LogAppelB2B));
		}

		return l;
	}

	@RolesAllowed("admin")
	public List<LogAppelB2BDTO> selectAllDTO() {
		System.out.println("List of LogAppelB2BDTO EJB :");
		ArrayList<LogAppelB2BDTO> liste = new ArrayList<LogAppelB2BDTO>();

		List<LogAppelB2B> projects = selectAll();
		for(LogAppelB2B project : projects) {
			liste.add(entityToDTO(project));
		}

		return liste;
	}

	public LogAppelB2BDTO findByIdDTO(int id) throws FinderException {
		return entityToDTO(findById(id));
	}

	public LogAppelB2BDTO findByCodeDTO(String code) throws FinderException {
		return entityToDTO(findByCode(code));
	}

	@Override
	public void error(LogAppelB2BDTO dto) {
		throw new EJBException("Test erreur EJB");
	}

	@Override
	public int selectCount() {
		return daoB2B.selectAll().size();
		//return 0;
	}

	@Override
	public void mergeDTO(LogAppelB2BDTO dto) throws EJBException {
		try {
			LogAppelB2BMapper mapper = new LogAppelB2BMapper();
			LogAppelB2B entity = null;
			//			if(dto.getId()!=null) {
			//				entity = daoB2B.findById(dto.getId());
			//				if(dto.getVersionObj()!=entity.getVersionObj()) {
			//					throw new OptimisticLockException(entity,
			//							"L'objet à été modifié depuis le dernier accés. Client ID : "+dto.getId()+" - Client Version objet : "+dto.getVersionObj()+" -Serveur Version Objet : "+entity.getVersionObj());
			//				} else {
			//					 entity = mapper.mapDtoToEntity(dto,entity);
			//				}
			//			}

			//daoB2B.merge(entity);
			daoB2B.detach(entity); //pour passer les controles
			enregistrerMerge(entity);
		} catch (Exception e) {
			e.printStackTrace();
			//throw new CreateException(e.getMessage());
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void persistDTO(LogAppelB2BDTO dto) throws CreateException {
		try {
			LogAppelB2BMapper mapper = new LogAppelB2BMapper();
			LogAppelB2B entity = mapper.mapDtoToEntity(dto,null);
			//daoB2B.persist(entity);
			enregistrerPersist(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CreateException(e.getMessage());
		}
	}

	@Override
	public void removeDTO(LogAppelB2BDTO dto) throws RemoveException {
		try {
			LogAppelB2BMapper mapper = new LogAppelB2BMapper();
			LogAppelB2B entity = mapper.mapDtoToEntity(dto,null);
			//daoB2B.remove(entity);
			supprimer(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}
	}

	@Override
	protected LogAppelB2B refresh(LogAppelB2B persistentInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateEntity(LogAppelB2B value) /*throws ExceptLgr*/ {
		try {
			String validationContext = "";
			validateAll(value,validationContext,false); //ancienne validation, extraction de l'annotation et appel
			//daoB2B.validate(value); //validation automatique via la JSR bean validation
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void validateEntityProperty(LogAppelB2B value, String propertyName) {
		try {
			String validationContext = "";
			validate(value, propertyName, validationContext);
			//daoB2B.validateField(value,propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void validateDTO(LogAppelB2BDTO dto) {
		try {
			LogAppelB2BMapper mapper = new LogAppelB2BMapper();
			LogAppelB2B entity = mapper.mapDtoToEntity(dto,null);
			validateEntity(entity);

			//validation automatique via la JSR bean validation
			//			BeanValidator<LogAppelB2BDTO> validator = new BeanValidator<LogAppelB2BDTO>(LogAppelB2BDTO.class);
			//			validator.validate(dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void validateDTOProperty(LogAppelB2BDTO dto, String propertyName) {
		try {
			LogAppelB2BMapper mapper = new LogAppelB2BMapper();
			LogAppelB2B entity = mapper.mapDtoToEntity(dto,null);
			validateEntityProperty(entity,propertyName);

			//validation automatique via la JSR bean validation
			//			BeanValidator<LogAppelB2BDTO> validator = new BeanValidator<LogAppelB2BDTO>(LogAppelB2BDTO.class);
			//			validator.validateField(dto,propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}

	}

}
