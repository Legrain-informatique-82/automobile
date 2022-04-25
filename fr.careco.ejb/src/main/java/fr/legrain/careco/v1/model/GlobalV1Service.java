package fr.legrain.careco.v1.model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import fr.legrain.data.AbstractApplicationDAOServer;


/**
 * Session Bean implementation class CarPartBean
 */
@Stateless
public class GlobalV1Service extends AbstractApplicationDAOServer<CarPart, FakeV1DTO> implements IGlobalV1ServiceRemote {

	static Logger logger = Logger.getLogger(GlobalV1Service.class);

	@Inject private IGlobalV1DAO dao;

	/**
	 * Default constructor. 
	 */
	public GlobalV1Service() {

	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 									
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										ENTITY
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(CarPart transientInstance) throws CreateException {

		validateEntity(transientInstance);

		dao.persist(transientInstance);
	}

	public void remove(CarPart persistentInstance) throws RemoveException {
		dao.remove(persistentInstance);
	}

	@Override
	public CarPart merge(CarPart detachedInstance) {
		validateEntity(detachedInstance);

		return dao.merge(detachedInstance);
	}

	public CarPart findById(int id) throws FinderException {
		return dao.findById(id);
	}

	public CarPart findByCode(String code) throws FinderException {
		return dao.findByCode(code);
	}

	@RolesAllowed("admin")
	public List<CarPart> selectAll() {
		return dao.selectAll();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										DTO
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<FakeV1DTO> findWithNamedQueryDTO(String queryName) throws FinderException {
		return null;
	}

	@Override
	public List<FakeV1DTO> findWithJPQLQueryDTO(String JPQLquery) throws FinderException {
		List<CarPart> entityList = dao.findWithJPQLQuery(JPQLquery);
		List<FakeV1DTO> l = null;
		if(entityList!=null) {
			l = listEntityToListDTO(entityList);
		}
		return l;
	}

	public FakeV1DTO entityToDTO(CarPart entity) {
		//		FakeV1DTO dto = new FakeV1DTO();
		//		dto.setId(entity.getIdTCivilite());
		//		dto.setCodeTCivilite(entity.getCodeTCivilite());
		//		return dto;
		v1Mapper mapper = new v1Mapper();
		return mapper.mapEntityToDto(entity, null);
	}

	public List<FakeV1DTO> listEntityToListDTO(List<CarPart> entity) {
		List<FakeV1DTO> l = new ArrayList<FakeV1DTO>();

		for (CarPart CarPart : entity) {
			l.add(entityToDTO(CarPart));
		}

		return l;
	}

	@RolesAllowed("admin")
	public List<FakeV1DTO> selectAllDTO() {
		System.out.println("List of FakeV1DTO EJB :");
		ArrayList<FakeV1DTO> liste = new ArrayList<FakeV1DTO>();

		List<CarPart> projects = selectAll();
		for(CarPart project : projects) {
			liste.add(entityToDTO(project));
		}

		return liste;
	}

	public FakeV1DTO findByIdDTO(int id) throws FinderException {
		return entityToDTO(findById(id));
	}

	public FakeV1DTO findByCodeDTO(String code) throws FinderException {
		return entityToDTO(findByCode(code));
	}

	@Override
	public void error(FakeV1DTO dto) {
		throw new EJBException("Test erreur EJB");
	}

	@Override
	public int selectCount() {
		return dao.selectAll().size();
		//return 0;
	}

	@Override
	public void mergeDTO(FakeV1DTO dto) throws EJBException {
		try {
			v1Mapper mapper = new v1Mapper();
			CarPart entity = null;
			//			if(dto.getId()!=null) {
			//				entity = dao.findById(dto.getId());
			//				if(dto.getVersionObj()!=entity.getVersionObj()) {
			//					throw new OptimisticLockException(entity,
			//							"L'objet à été modifié depuis le dernier accés. Client ID : "+dto.getId()+" - Client Version objet : "+dto.getVersionObj()+" -Serveur Version Objet : "+entity.getVersionObj());
			//				} else {
			//					 entity = mapper.mapDtoToEntity(dto,entity);
			//				}
			//			}

			//dao.merge(entity);
			dao.detach(entity); //pour passer les controles
			enregistrerMerge(entity);
		} catch (Exception e) {
			e.printStackTrace();
			//throw new CreateException(e.getMessage());
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void persistDTO(FakeV1DTO dto) throws CreateException {
		try {
			v1Mapper mapper = new v1Mapper();
			CarPart entity = mapper.mapDtoToEntity(dto,null);
			//dao.persist(entity);
			enregistrerPersist(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CreateException(e.getMessage());
		}
	}

	@Override
	public void removeDTO(FakeV1DTO dto) throws RemoveException {
		try {
			v1Mapper mapper = new v1Mapper();
			CarPart entity = mapper.mapDtoToEntity(dto,null);
			//dao.remove(entity);
			supprimer(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}
	}

	@Override
	protected CarPart refresh(CarPart persistentInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateEntity(CarPart value) /*throws ExceptLgr*/ {
		try {
			String validationContext = "";
			validateAll(value,validationContext,false); //ancienne validation, extraction de l'annotation et appel
			//dao.validate(value); //validation automatique via la JSR bean validation
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void validateEntityProperty(CarPart value, String propertyName) {
		try {
			String validationContext = "";
			validate(value, propertyName, validationContext);
			//dao.validateField(value,propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void validateDTO(FakeV1DTO dto) {
		try {
			v1Mapper mapper = new v1Mapper();
			CarPart entity = mapper.mapDtoToEntity(dto,null);
			validateEntity(entity);

			//validation automatique via la JSR bean validation
			//			BeanValidator<FakeV1DTO> validator = new BeanValidator<FakeV1DTO>(FakeV1DTO.class);
			//			validator.validate(dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void validateDTOProperty(FakeV1DTO dto, String propertyName) {
		try {
			v1Mapper mapper = new v1Mapper();
			CarPart entity = mapper.mapDtoToEntity(dto,null);
			validateEntityProperty(entity,propertyName);

			//validation automatique via la JSR bean validation
			//			BeanValidator<FakeV1DTO> validator = new BeanValidator<FakeV1DTO>(FakeV1DTO.class);
			//			validator.validateField(dto,propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}

	}

}
