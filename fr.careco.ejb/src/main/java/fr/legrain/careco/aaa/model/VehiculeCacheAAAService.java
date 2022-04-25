package fr.legrain.careco.aaa.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.persistence.NoResultException;

import org.apache.log4j.Logger;

import fr.asso.aaa.sivin.BaseDeDonneeInaccessible;
import fr.asso.aaa.sivin.NumeroImmatriculationInconnu;
import fr.asso.aaa.sivin.Quota;
import fr.asso.aaa.sivin.WSSiVinConsulter;
import fr.asso.aaa.sivin.WSSiVinConsulterPortType;
import fr.asso.aaa.sivin.xsd.Vehicule;
import fr.careco.aaa.ws.clientsample.HeaderHandlerResolver;
//import fr.careco.aaa.ws.BaseDeDonneeInaccessible_Exception;
//import fr.careco.aaa.ws.NumeroImmatriculationInconnu_Exception;
//import fr.careco.aaa.ws.Quota_Exception;
//import fr.careco.aaa.ws.Vehicule;
//import fr.careco.aaa.ws.WSSiVinConsulter;
//import fr.careco.aaa.ws.WSSiVinConsulterPortType;
//import fr.careco.aaa.ws.clientsample.HeaderHandlerResolver;
import fr.legrain.data.AbstractApplicationDAOServer;


/**
 * Session Bean implementation class VehiculeCacheAAABean
 */
@Stateless
//@DeclareRoles("admin")
@WebService
//@Interceptors(net.bull.javamelody.MonitoringInterceptor.class)
public class VehiculeCacheAAAService extends AbstractApplicationDAOServer<VehiculeCacheAAA, VehiculeCacheAAADTO> implements IVehiculeCacheAAAServiceRemote,IVehiculeCacheAAAServiceLocal {

	static Logger logger = Logger.getLogger(VehiculeCacheAAAService.class);

	@Inject private IVehiculeCacheAAADAO dao;

	/**
	 * Default constructor. 
	 */
	public VehiculeCacheAAAService() {

	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										Webservice AAA
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public VehiculeCacheAAA rechercheDansCache(String immatriculation, String vin) throws EJBException {
		VehiculeCacheAAA vCache = null;
		
		if(immatriculation!=null && !immatriculation.equals("")) {
			logger.debug("Recherche par immatriculation : "+immatriculation);
			System.out.println("sys : Recherche par immatriculation : "+immatriculation);

			try {
				vCache = dao.findByImmat(immatriculation);
			} catch(NoResultException ex) {
				logger.debug("Immatriculation "+immatriculation+" pas dans le cache");
				System.out.println("sys : Immatriculation "+immatriculation+" pas dans le cache");
			}
		} else if(vin!=null && !vin.equals("")) {
			logger.debug("Recherche par vin : "+immatriculation);
			System.out.println("sys : Recherche par vin : "+immatriculation);

			try {
				vCache = dao.findByVIN(immatriculation);
			} catch(NoResultException ex) {
				logger.debug("VIN "+immatriculation+" pas dans le cache");
				System.out.println("sys : VIN "+immatriculation+" pas dans le cache");
			}
		}
		
		return vCache;
	}
	
	public VehiculeCacheAAA wsSiVinConsulterVehiculeParImmatOuVin(String immatriculation, String vin) throws EJBException {
		//recherche B2B
		return wsSiVinConsulterVehiculeParImmatOuVinProprio(immatriculation,vin,null,false);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public VehiculeCacheAAA wsSiVinConsulterVehiculeParImmatOuVinProprio(String immatriculation, String vin, String debutNomProprietaire, boolean sansCache) throws EJBException {
		
		VehiculeCacheAAA vCache = null;
		
		if(!sansCache) {
			vCache = rechercheDansCache(immatriculation,vin);
		}
		
//		if(immatriculation!=null && !immatriculation.equals("")) {
//			logger.debug("Recherche par immatriculation : "+immatriculation);
//			System.out.println("sys : Recherche par immatriculation : "+immatriculation);
//
//			try {
//				vCache = dao.findByImmat(immatriculation);
//			} catch(NoResultException ex) {
//				logger.debug("Immatriculation "+immatriculation+" pas dans le cache");
//				System.out.println("sys : Immatriculation "+immatriculation+" pas dans le cache");
//			}
//		} else if(vin!=null && !vin.equals("")) {
//			logger.debug("Recherche par vin : "+immatriculation);
//			System.out.println("sys : Recherche par vin : "+immatriculation);
//
//			try {
//				vCache = dao.findByVIN(immatriculation);
//			} catch(NoResultException ex) {
//				logger.debug("VIN "+immatriculation+" pas dans le cache");
//				System.out.println("sys : VIN "+immatriculation+" pas dans le cache");
//			}
//		}
		
		//VehiculeCacheAAA vCache = null; //force webservice pour test
		
		VehiculeCacheAAAMapper mapper = new VehiculeCacheAAAMapper();
		if(vCache==null) {
			
			if(immatriculation!=null && !immatriculation.equals("")) {
				logger.debug("Immatriculation "+immatriculation+" pas dans le cache, appel du webservice ...");
				System.out.println("sys : Immatriculation "+immatriculation+" pas dans le cache, appel du webservice ...");
			} else if(vin!=null && !vin.equals("")) {
				logger.debug("VIN "+vin+" pas dans le cache, appel du webservice ...");
				System.out.println("sys : VIN "+vin+" pas dans le cache, appel du webservice ...");
			}
		

			WSSiVinConsulter service1 = new WSSiVinConsulter();
			logger.debug("Create Web Service...");
			System.out.println("sys : Create Web Service...");

			HeaderHandlerResolver handlerResolver = null;
			WSSiVinConsulterPortType port1 = null;

			try {
				Vehicule v = null;
				if(immatriculation!=null && !immatriculation.trim().equals("")) {
					if(debutNomProprietaire==null) {
						//B2B
						/********************************************/
						handlerResolver = new HeaderHandlerResolver(HeaderHandlerResolver.TYPE_B2B);
						service1.setHandlerResolver(handlerResolver);
						/********************************************/

						port1 = service1.getWSSiVinConsulterSOAP12Port();
						logger.debug("Call Web Service Operation...");
						System.out.println("sys : Call Web Service Operation...");
						v = port1.wsSiVinConsulterVehiculeParImmat(immatriculation,null,null,null,null);
					} else {
						//B2C
						/********************************************/
						handlerResolver = new HeaderHandlerResolver(HeaderHandlerResolver.TYPE_B2C);
						service1.setHandlerResolver(handlerResolver);
						/********************************************/

						port1 = service1.getWSSiVinConsulterSOAP12Port();
						logger.debug("Call Web Service Operation...");
						System.out.println("sys : Call Web Service Operation...");
						debutNomProprietaire = prepareNomPourWs(debutNomProprietaire);
						v = port1.wsSiVinConsulterVehiculeParImmat(immatriculation,debutNomProprietaire,null,null,null);
					}
				} else if(vin!=null && !vin.trim().equals("")) {
					//B2B
					/********************************************/
					handlerResolver = new HeaderHandlerResolver(HeaderHandlerResolver.TYPE_B2B);
					service1.setHandlerResolver(handlerResolver);
					/********************************************/

					port1 = service1.getWSSiVinConsulterSOAP12Port();
					v = port1.wsSiVinConsulterVehiculeParVin(vin,null,null,null,null);
				}
				logger.debug("Server said: " + v);
				System.out.println("sys : Server said: " + v);
				
				if(v!=null) {
					logger.debug("Webservice OK, enregistrement dans le cache");
					System.out.println("sys : Webservice OK, enregistrement dans le cache");
					vCache = mapper.mapWSToEntity(v, vCache);
					dao.persist(vCache);
				} else {
					logger.debug("Erreur du webservice, enregistrement dans le cache de l'immatriculation sans aucune autre infos pour éviter de rappeler le webservice");
					System.out.println("sys : Erreur du webservice, enregistrement dans le cache de l'immatriculation sans aucune autre infos pour éviter de rappeler le webservice");
					//vCache = mapper.mapWSToEntity(v, vCache);
					vCache = new VehiculeCacheAAA();
					vCache.setImmat_SIV(immatriculation);
					dao.persist(vCache);
				}

//			} catch (NumeroImmatriculationInconnu_Exception e) {
//				//logger.error("",e);
//				if(e.getMessage()!=null) {
//					logger.error(e.getMessage());
//				}
//				//e.printStackTrace();
//				throw new EJBException(e.getMessage());
//			} catch (BaseDeDonneeInaccessible_Exception e) {
//				logger.error("",e);
//				e.printStackTrace();
//				throw new EJBException(e.getMessage());
//			} catch (Quota_Exception e) {
//				logger.error("",e);
//				e.printStackTrace();
//				throw new EJBException(e.getMessage());
			} catch (NumeroImmatriculationInconnu e) {
				//logger.error("",e);
				if(e.getMessage()!=null) {
					logger.error(e.getMessage());
				}
				//e.printStackTrace();
				throw new EJBException(e.getMessage());
			} catch (BaseDeDonneeInaccessible e) {
				logger.error("",e);
				e.printStackTrace();
				throw new EJBException(e.getMessage());
			} catch (Quota e) {
				logger.error("",e);
				e.printStackTrace();
				throw new EJBException(e.getMessage());
			} catch (Exception e) {
				logger.error("",e);
				e.printStackTrace();
				throw new EJBException(e.getMessage());
			}
		} else {
			logger.debug("Immatriculation "+immatriculation+" dans le cache");
			System.out.println("sys : Immatriculation "+immatriculation+" dans le cache");
		
		}
		return vCache;
	}
	
	public String prepareNomPourWs(String debutNomProprietaire) {
		//le webservice AAA pour un appel B2C nécessite les 3 première lettre du nom propriétaire en majuscule sans aucun caractère spécial
		debutNomProprietaire = debutNomProprietaire.replaceAll("-", "").replaceAll(" ", "").replaceAll("'", "");
		debutNomProprietaire = debutNomProprietaire.toUpperCase();
		debutNomProprietaire = debutNomProprietaire.trim();
		debutNomProprietaire = debutNomProprietaire.substring(0,3);
		while(debutNomProprietaire.length()<3) {
			debutNomProprietaire+=" ";
		}
		return debutNomProprietaire;
	}
	
	public List<String> selectAllMarque() {
		return dao.selectAllMarque();
	}
	
	public List<String> selectAllModele(String marque) {
		return dao.selectAllModele(marque);
	}
	
	public List<String> selectAllAnnee() {
		return dao.selectAllAnnee();
	}
	
	public Map<String, String> rechercheCNITAnnee(String marque, String modele, String anneeMin, String anneeMax){
		return dao.rechercheCNITAnnee(marque, modele, anneeMin, anneeMax);
	}
	
	public List<VehiculeCacheAAA> selectAllLimit(int startPosition, int maxResults) {
		return dao.selectAll(startPosition, maxResults);
	}
	
	public long count() {
		return dao.count();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										ENTITY
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(VehiculeCacheAAA transientInstance) throws CreateException {

		validateEntity(transientInstance);

		dao.persist(transientInstance);
	}

	public void remove(VehiculeCacheAAA persistentInstance) throws RemoveException {
		dao.remove(persistentInstance);
	}

	@Override
	public VehiculeCacheAAA merge(VehiculeCacheAAA detachedInstance) {
		validateEntity(detachedInstance);

		return dao.merge(detachedInstance);
	}

	public VehiculeCacheAAA findById(int id) throws FinderException {
		return dao.findById(id);
	}

	public VehiculeCacheAAA findByCode(String code) throws FinderException {
		return dao.findByCode(code);
	}

	@RolesAllowed("admin")
	public List<VehiculeCacheAAA> selectAll() {
		return dao.selectAll();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										DTO
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<VehiculeCacheAAADTO> findWithNamedQueryDTO(String queryName) throws FinderException {
		return null;
	}

	@Override
	public List<VehiculeCacheAAADTO> findWithJPQLQueryDTO(String JPQLquery) throws FinderException {
		List<VehiculeCacheAAA> entityList = dao.findWithJPQLQuery(JPQLquery);
		List<VehiculeCacheAAADTO> l = null;
		if(entityList!=null) {
			l = listEntityToListDTO(entityList);
		}
		return l;
	}

	public VehiculeCacheAAADTO entityToDTO(VehiculeCacheAAA entity) {
		//		VehiculeCacheAAADTO dto = new VehiculeCacheAAADTO();
		//		dto.setId(entity.getIdTCivilite());
		//		dto.setCodeTCivilite(entity.getCodeTCivilite());
		//		return dto;
		VehiculeCacheAAAMapper mapper = new VehiculeCacheAAAMapper();
		return mapper.mapEntityToDto(entity, null);
	}

	public List<VehiculeCacheAAADTO> listEntityToListDTO(List<VehiculeCacheAAA> entity) {
		List<VehiculeCacheAAADTO> l = new ArrayList<VehiculeCacheAAADTO>();

		for (VehiculeCacheAAA VehiculeCacheAAA : entity) {
			l.add(entityToDTO(VehiculeCacheAAA));
		}

		return l;
	}

	@RolesAllowed("admin")
	public List<VehiculeCacheAAADTO> selectAllDTO() {
		System.out.println("List of VehiculeCacheAAADTO EJB :");
		ArrayList<VehiculeCacheAAADTO> liste = new ArrayList<VehiculeCacheAAADTO>();

		List<VehiculeCacheAAA> projects = selectAll();
		for(VehiculeCacheAAA project : projects) {
			liste.add(entityToDTO(project));
		}

		return liste;
	}

	public VehiculeCacheAAADTO findByIdDTO(int id) throws FinderException {
		return entityToDTO(findById(id));
	}

	public VehiculeCacheAAADTO findByCodeDTO(String code) throws FinderException {
		return entityToDTO(findByCode(code));
	}

	@Override
	public void error(VehiculeCacheAAADTO dto) {
		throw new EJBException("Test erreur EJB");
	}

	@Override
	public int selectCount() {
		return dao.selectAll().size();
		//return 0;
	}

	@Override
	public void mergeDTO(VehiculeCacheAAADTO dto) throws EJBException {
		try {
			VehiculeCacheAAAMapper mapper = new VehiculeCacheAAAMapper();
			VehiculeCacheAAA entity = null;
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
	public void persistDTO(VehiculeCacheAAADTO dto) throws CreateException {
		try {
			VehiculeCacheAAAMapper mapper = new VehiculeCacheAAAMapper();
			VehiculeCacheAAA entity = mapper.mapDtoToEntity(dto,null);
			//dao.persist(entity);
			enregistrerPersist(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CreateException(e.getMessage());
		}
	}

	@Override
	public void removeDTO(VehiculeCacheAAADTO dto) throws RemoveException {
		try {
			VehiculeCacheAAAMapper mapper = new VehiculeCacheAAAMapper();
			VehiculeCacheAAA entity = mapper.mapDtoToEntity(dto,null);
			//dao.remove(entity);
			supprimer(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}
	}

	@Override
	protected VehiculeCacheAAA refresh(VehiculeCacheAAA persistentInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateEntity(VehiculeCacheAAA value) /*throws ExceptLgr*/ {
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
	public void validateEntityProperty(VehiculeCacheAAA value, String propertyName) {
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
	public void validateDTO(VehiculeCacheAAADTO dto) {
		try {
			VehiculeCacheAAAMapper mapper = new VehiculeCacheAAAMapper();
			VehiculeCacheAAA entity = mapper.mapDtoToEntity(dto,null);
			validateEntity(entity);

			//validation automatique via la JSR bean validation
			//			BeanValidator<VehiculeCacheAAADTO> validator = new BeanValidator<VehiculeCacheAAADTO>(VehiculeCacheAAADTO.class);
			//			validator.validate(dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void validateDTOProperty(VehiculeCacheAAADTO dto, String propertyName) {
		try {
			VehiculeCacheAAAMapper mapper = new VehiculeCacheAAAMapper();
			VehiculeCacheAAA entity = mapper.mapDtoToEntity(dto,null);
			validateEntityProperty(entity,propertyName);

			//validation automatique via la JSR bean validation
			//			BeanValidator<VehiculeCacheAAADTO> validator = new BeanValidator<VehiculeCacheAAADTO>(VehiculeCacheAAADTO.class);
			//			validator.validateField(dto,propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}

	}

}
