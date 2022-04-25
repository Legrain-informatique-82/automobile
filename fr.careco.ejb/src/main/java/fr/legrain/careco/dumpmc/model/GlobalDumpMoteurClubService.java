package fr.legrain.careco.dumpmc.model;

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
 * Session Bean implementation class StockMoteurClubBean
 */
@Stateless
public class GlobalDumpMoteurClubService extends AbstractApplicationDAOServer<StockMoteurClub, FakeMoteurClubDTO> implements IGlobalDumpMoteurClubServiceRemote {

	static Logger logger = Logger.getLogger(GlobalDumpMoteurClubService.class);

	@Inject private IGlobalDumpMcDAO dao;

	/**
	 * Default constructor. 
	 */
	public GlobalDumpMoteurClubService() {

	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 									
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										ENTITY
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(StockMoteurClub transientInstance) throws CreateException {

		validateEntity(transientInstance);

		dao.persist(transientInstance);
	}

	public void remove(StockMoteurClub persistentInstance) throws RemoveException {
		dao.remove(persistentInstance);
	}

	@Override
	public StockMoteurClub merge(StockMoteurClub detachedInstance) {
		validateEntity(detachedInstance);

		return dao.merge(detachedInstance);
	}

	public StockMoteurClub findById(int id) throws FinderException {
		return dao.findById(id);
	}

	public StockMoteurClub findByCode(String code) throws FinderException {
		return dao.findByCode(code);
	}

	@RolesAllowed("admin")
	public List<StockMoteurClub> selectAll() {
		return dao.selectAll();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										DTO
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<FakeMoteurClubDTO> findWithNamedQueryDTO(String queryName) throws FinderException {
		return null;
	}

	@Override
	public List<FakeMoteurClubDTO> findWithJPQLQueryDTO(String JPQLquery) throws FinderException {
		List<StockMoteurClub> entityList = dao.findWithJPQLQuery(JPQLquery);
		List<FakeMoteurClubDTO> l = null;
		if(entityList!=null) {
			l = listEntityToListDTO(entityList);
		}
		return l;
	}

	public FakeMoteurClubDTO entityToDTO(StockMoteurClub entity) {
		//		FakeMoteurClubDTO dto = new FakeMoteurClubDTO();
		//		dto.setId(entity.getIdTCivilite());
		//		dto.setCodeTCivilite(entity.getCodeTCivilite());
		//		return dto;
		DumpMoteurClubMapper mapper = new DumpMoteurClubMapper();
		return mapper.mapEntityToDto(entity, null);
	}

	public List<FakeMoteurClubDTO> listEntityToListDTO(List<StockMoteurClub> entity) {
		List<FakeMoteurClubDTO> l = new ArrayList<FakeMoteurClubDTO>();

		for (StockMoteurClub StockMoteurClub : entity) {
			l.add(entityToDTO(StockMoteurClub));
		}

		return l;
	}

	@RolesAllowed("admin")
	public List<FakeMoteurClubDTO> selectAllDTO() {
		System.out.println("List of FakeMoteurClubDTO EJB :");
		ArrayList<FakeMoteurClubDTO> liste = new ArrayList<FakeMoteurClubDTO>();

		List<StockMoteurClub> projects = selectAll();
		for(StockMoteurClub project : projects) {
			liste.add(entityToDTO(project));
		}

		return liste;
	}

	public FakeMoteurClubDTO findByIdDTO(int id) throws FinderException {
		return entityToDTO(findById(id));
	}

	public FakeMoteurClubDTO findByCodeDTO(String code) throws FinderException {
		return entityToDTO(findByCode(code));
	}

	@Override
	public void error(FakeMoteurClubDTO dto) {
		throw new EJBException("Test erreur EJB");
	}

	@Override
	public int selectCount() {
		return dao.selectAll().size();
		//return 0;
	}

	@Override
	public void mergeDTO(FakeMoteurClubDTO dto) throws EJBException {
		try {
			DumpMoteurClubMapper mapper = new DumpMoteurClubMapper();
			StockMoteurClub entity = null;
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
	public void persistDTO(FakeMoteurClubDTO dto) throws CreateException {
		try {
			DumpMoteurClubMapper mapper = new DumpMoteurClubMapper();
			StockMoteurClub entity = mapper.mapDtoToEntity(dto,null);
			//dao.persist(entity);
			enregistrerPersist(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CreateException(e.getMessage());
		}
	}

	@Override
	public void removeDTO(FakeMoteurClubDTO dto) throws RemoveException {
		try {
			DumpMoteurClubMapper mapper = new DumpMoteurClubMapper();
			StockMoteurClub entity = mapper.mapDtoToEntity(dto,null);
			//dao.remove(entity);
			supprimer(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}
	}

	@Override
	protected StockMoteurClub refresh(StockMoteurClub persistentInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateEntity(StockMoteurClub value) /*throws ExceptLgr*/ {
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
	public void validateEntityProperty(StockMoteurClub value, String propertyName) {
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
	public void validateDTO(FakeMoteurClubDTO dto) {
		try {
			DumpMoteurClubMapper mapper = new DumpMoteurClubMapper();
			StockMoteurClub entity = mapper.mapDtoToEntity(dto,null);
			validateEntity(entity);

			//validation automatique via la JSR bean validation
			//			BeanValidator<FakeMoteurClubDTO> validator = new BeanValidator<FakeMoteurClubDTO>(FakeMoteurClubDTO.class);
			//			validator.validate(dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void validateDTOProperty(FakeMoteurClubDTO dto, String propertyName) {
		try {
			DumpMoteurClubMapper mapper = new DumpMoteurClubMapper();
			StockMoteurClub entity = mapper.mapDtoToEntity(dto,null);
			validateEntityProperty(entity,propertyName);

			//validation automatique via la JSR bean validation
			//			BeanValidator<FakeMoteurClubDTO> validator = new BeanValidator<FakeMoteurClubDTO>(FakeMoteurClubDTO.class);
			//			validator.validateField(dto,propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}

	}

}
