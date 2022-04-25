package fr.careco.blueway.ws.model;

import java.util.ArrayList;
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
public class AutorisationService extends AbstractApplicationDAOServer<AutorisationB2B, AutorisationB2BDTO> implements IAutorisationServiceRemote {

	static Logger logger = Logger.getLogger(AutorisationService.class);

	@Inject private IAutorisationB2BDAO daoB2B;
	@Inject private IAutorisationB2CDAO daoB2C;

	/**
	 * Default constructor. 
	 */
	public AutorisationService() {

	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public AutorisationB2B findByLoginB2B(String login, String password) {
		return daoB2B.findByLogin(login, password);
	}
	public AutorisationB2C findByLoginB2C(String login, String password) {
		return daoB2C.findByLogin(login, password);
	}

	public AutorisationB2C enregistrerMerge(AutorisationB2C persistentInstance) throws Exception {
		return daoB2C.merge(persistentInstance);
	}
	
	public List<AutorisationB2B> selectAll(String debut) {
		return daoB2B.selectAll(debut);
	}
	
	public List<AutorisationB2C> selectAllB2C(String debut) {
		return daoB2C.selectAll(debut);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										ENTITY
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(AutorisationB2B transientInstance) throws CreateException {

		validateEntity(transientInstance);

		daoB2B.persist(transientInstance);
	}

	public void remove(AutorisationB2B persistentInstance) throws RemoveException {
		daoB2B.remove(persistentInstance);
	}
	
	public void remove(AutorisationB2C persistentInstance) throws RemoveException {
		daoB2C.remove(persistentInstance);
	}

	@Override
	public AutorisationB2B merge(AutorisationB2B detachedInstance) {
		validateEntity(detachedInstance);

		return daoB2B.merge(detachedInstance);
	}
	
	public AutorisationB2C merge(AutorisationB2C persistentInstance) {
		return daoB2C.merge(persistentInstance);
	}
	

	public AutorisationB2B findById(int id) throws FinderException {
		return daoB2B.findById(id);
	}

	public AutorisationB2B findByCode(String code) throws FinderException {
		return daoB2B.findByCode(code);
	}

	@RolesAllowed("admin")
	public List<AutorisationB2B> selectAll() {
		return daoB2B.selectAll();
	}
	
	public List<AutorisationB2C> selectAllB2C() {
		return daoB2C.selectAll();
	}

	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										DTO
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<AutorisationB2BDTO> findWithNamedQueryDTO(String queryName) throws FinderException {
		return null;
	}

	@Override
	public List<AutorisationB2BDTO> findWithJPQLQueryDTO(String JPQLquery) throws FinderException {
		List<AutorisationB2B> entityList = daoB2B.findWithJPQLQuery(JPQLquery);
		List<AutorisationB2BDTO> l = null;
		if(entityList!=null) {
			l = listEntityToListDTO(entityList);
		}
		return l;
	}

	public AutorisationB2BDTO entityToDTO(AutorisationB2B entity) {
		//		AutorisationB2BDTO dto = new AutorisationB2BDTO();
		//		dto.setId(entity.getIdTCivilite());
		//		dto.setCodeTCivilite(entity.getCodeTCivilite());
		//		return dto;
		AutorisationB2BMapper mapper = new AutorisationB2BMapper();
		return mapper.mapEntityToDto(entity, null);
	}

	public List<AutorisationB2BDTO> listEntityToListDTO(List<AutorisationB2B> entity) {
		List<AutorisationB2BDTO> l = new ArrayList<AutorisationB2BDTO>();

		for (AutorisationB2B AutorisationB2B : entity) {
			l.add(entityToDTO(AutorisationB2B));
		}

		return l;
	}

	@RolesAllowed("admin")
	public List<AutorisationB2BDTO> selectAllDTO() {
		System.out.println("List of AutorisationB2BDTO EJB :");
		ArrayList<AutorisationB2BDTO> liste = new ArrayList<AutorisationB2BDTO>();

		List<AutorisationB2B> projects = selectAll();
		for(AutorisationB2B project : projects) {
			liste.add(entityToDTO(project));
		}

		return liste;
	}

	public AutorisationB2BDTO findByIdDTO(int id) throws FinderException {
		return entityToDTO(findById(id));
	}

	public AutorisationB2BDTO findByCodeDTO(String code) throws FinderException {
		return entityToDTO(findByCode(code));
	}

	@Override
	public void error(AutorisationB2BDTO dto) {
		throw new EJBException("Test erreur EJB");
	}

	@Override
	public int selectCount() {
		return daoB2B.selectAll().size();
		//return 0;
	}

	@Override
	public void mergeDTO(AutorisationB2BDTO dto) throws EJBException {
		try {
			AutorisationB2BMapper mapper = new AutorisationB2BMapper();
			AutorisationB2B entity = null;
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
	public void persistDTO(AutorisationB2BDTO dto) throws CreateException {
		try {
			AutorisationB2BMapper mapper = new AutorisationB2BMapper();
			AutorisationB2B entity = mapper.mapDtoToEntity(dto,null);
			//daoB2B.persist(entity);
			enregistrerPersist(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CreateException(e.getMessage());
		}
	}

	@Override
	public void removeDTO(AutorisationB2BDTO dto) throws RemoveException {
		try {
			AutorisationB2BMapper mapper = new AutorisationB2BMapper();
			AutorisationB2B entity = mapper.mapDtoToEntity(dto,null);
			//daoB2B.remove(entity);
			supprimer(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}
	}

	@Override
	protected AutorisationB2B refresh(AutorisationB2B persistentInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateEntity(AutorisationB2B value) /*throws ExceptLgr*/ {
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
	public void validateEntityProperty(AutorisationB2B value, String propertyName) {
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
	public void validateDTO(AutorisationB2BDTO dto) {
		try {
			AutorisationB2BMapper mapper = new AutorisationB2BMapper();
			AutorisationB2B entity = mapper.mapDtoToEntity(dto,null);
			validateEntity(entity);

			//validation automatique via la JSR bean validation
			//			BeanValidator<AutorisationB2BDTO> validator = new BeanValidator<AutorisationB2BDTO>(AutorisationB2BDTO.class);
			//			validator.validate(dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void validateDTOProperty(AutorisationB2BDTO dto, String propertyName) {
		try {
			AutorisationB2BMapper mapper = new AutorisationB2BMapper();
			AutorisationB2B entity = mapper.mapDtoToEntity(dto,null);
			validateEntityProperty(entity,propertyName);

			//validation automatique via la JSR bean validation
			//			BeanValidator<AutorisationB2BDTO> validator = new BeanValidator<AutorisationB2BDTO>(AutorisationB2BDTO.class);
			//			validator.validateField(dto,propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}

	}

}
