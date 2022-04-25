package fr.legrain.tiers.service;

import java.io.FileInputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import fr.legrain.bdg.model.mapping.mapper.FicheCarecoMapper;
import fr.legrain.bdg.tiers.service.local.IUserServiceLocal;
import fr.legrain.bdg.tiers.service.remote.IFicheCarecoServiceRemote;
import fr.legrain.data.AbstractApplicationDAOServer;
import fr.legrain.tiers.dao.IFicheCarecoDAO;
import fr.legrain.tiers.dto.FicheCarecoDTO;
import fr.legrain.tiers.model.FicheCareco;

/**
 * Session Bean implementation class FicheCarecoBean
 */
@Stateless
//@DeclareRoles("admin")
@WebService
public class FicheCarecoService extends AbstractApplicationDAOServer<FicheCareco, FicheCarecoDTO> implements IFicheCarecoServiceRemote {

	static Logger logger = Logger.getLogger(FicheCarecoService.class);
	
	@EJB IUserServiceLocal userService;

	@Inject private IFicheCarecoDAO dao;

	/**
	 * Default constructor. 
	 */
	public FicheCarecoService() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void init() {
//		try {
//			importFromExcelFile(null);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public List<FicheCareco> findByCNIT_TypeCG(String typeCG, Date date1ereMiseEnCirculation) {
		return dao.findByCNIT_TypeCG(typeCG, date1ereMiseEnCirculation);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										Utilitaire
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private boolean isRowEmpty(Row row) {
	    for (int c = row.getFirstCellNum(); c <= row.getLastCellNum(); c++) {
	        Cell cell = row.getCell(c);
	        if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
	            return false;
	    }
	    return true;
	}

	public void importFromExcelFile(String excelXLSXFileName) throws ParseException {
		//excelXLSXFileName = "/home/nicolas/Téléchargements/fiupdate3 echantillon.xlsx";
		excelXLSXFileName = "/home/nicolas/Téléchargements/fiupdate3.xlsx";
		Vector vectorDataExcelXLSX = new Vector();


		Vector<Vector<Cell>> vectorData = new Vector<Vector<Cell>>();

		try {
			FileInputStream fileInputStream = new FileInputStream(excelXLSXFileName);

			XSSFWorkbook xssfWorkBook = new XSSFWorkbook(fileInputStream);

			// Read data at sheet 0
			XSSFSheet xssfSheet = xssfWorkBook.getSheetAt(0);

			Iterator rowIteration = xssfSheet.rowIterator();
			
			Vector<Cell> vectorCellEachRowData = null;
			Cell cell = null;
			for(Row row : xssfSheet) {
				if(!isRowEmpty(row)) {
				 vectorCellEachRowData = new Vector<Cell>();
				   for(int cn=0; cn<row.getLastCellNum(); cn++) {
				       // If the cell is missing from the file, generate a blank one
				       // (Works by specifying a MissingCellPolicy)
				       cell = row.getCell(cn, Row.CREATE_NULL_AS_BLANK);
				       vectorCellEachRowData.addElement(cell);
				       // Print the cell for debugging
				       //System.out.println("CELL: " + cn + " --> " + cell.toString());
				   }
				   vectorData.addElement(vectorCellEachRowData);
				}
			}

//			// Looping every row at sheet 0
//			while (rowIteration.hasNext()) {
//				XSSFRow xssfRow = (XSSFRow) rowIteration.next();
//				Iterator cellIteration = xssfRow.cellIterator();
//
//				Vector vectorCellEachRowData = new Vector();
//
//				// Looping every cell in each row at sheet 0
//				while (cellIteration.hasNext()) {
//					XSSFCell xssfCell = (XSSFCell) cellIteration.next();
//					vectorCellEachRowData.addElement(xssfCell);
//				}
//
//				vectorData.addElement(vectorCellEachRowData);
//			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}


		// Looping every row data in vector
		Vector<Cell> vectorCellEachRowData = null;
		FicheCareco f = null;
		for(int i=1; i<vectorData.size(); i++) { //1 => on saut les lignes de titre
			vectorCellEachRowData = (Vector<Cell>) vectorData.get(i);
			// looping every cell in each row
			
			//for(int j=0; j<vectorCellEachRowData.size(); j++) {
				//System.out.println("ligne "+i);
				//System.out.print(vectorCellEachRowData.get(j).toString()+" | ");
				f = new FicheCareco(
						vectorCellEachRowData.get(0).toString(), //id 
						vectorCellEachRowData.get(1).toString(), //marque 
						vectorCellEachRowData.get(2).toString(), //modele 
						vectorCellEachRowData.get(3).toString(), //mode
						vectorCellEachRowData.get(4).toString(), //genre 
						vectorCellEachRowData.get(5).toString(), //cm3 
						vectorCellEachRowData.get(6).toString(), //cyl 
						vectorCellEachRowData.get(7).toString(), //sppes 
						vectorCellEachRowData.get(8).toString(), //cv
						vectorCellEachRowData.get(9).getDateCellValue(), //dateDe 
						vectorCellEachRowData.get(10).getDateCellValue(), //dateA 
						vectorCellEachRowData.get(11).toString(), //typeCG
						vectorCellEachRowData.get(12).toString(), //typeMot 
						vectorCellEachRowData.get(13).toString(), //codeMot 
						vectorCellEachRowData.get(14).toString(), //obsMot 
						vectorCellEachRowData.get(15).toString(), //refBte
						vectorCellEachRowData.get(16).toString(), //codeBte 
						vectorCellEachRowData.get(17).toString(), //genreBte 
						vectorCellEachRowData.get(18).toString(), //obsBte 
						vectorCellEachRowData.get(19).toString(), //cetteBoite
						vectorCellEachRowData.get(20).toString(), //avecCetteBoite 
						vectorCellEachRowData.get(21).toString(), //avecCetteAutreBoite
						vectorCellEachRowData.get(22).toString(), //pivotBoiteDeVitesse 
						vectorCellEachRowData.get(23).toString(), //ceTypeMoteurEstCompatibleAvec
						vectorCellEachRowData.get(24).toString(), //ceMoteur 
						vectorCellEachRowData.get(25).toString() //pivotMoteurs
						);
			//}
			dao.persist(f);
			//System.out.println("");
		}

	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										ENTITY
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(FicheCareco transientInstance) throws CreateException {
		
//		transientInstance.setQuiCree(userService.findUserLoggedId());
//		transientInstance.setQuiModif(userService.findUserLoggedId());

		validateEntity(transientInstance);

		dao.persist(transientInstance);
	}

	public void remove(FicheCareco persistentInstance) throws RemoveException {
		dao.remove(persistentInstance);
	}

	@Override
	public FicheCareco merge(FicheCareco detachedInstance) {
		
//		detachedInstance.setQuiCree(userService.findUserLoggedId());
//		detachedInstance.setQuiModif(userService.findUserLoggedId());
		
		validateEntity(detachedInstance);

		return dao.merge(detachedInstance);
	}

	public FicheCareco findById(int id) throws FinderException {
		return dao.findById(id);
	}

	public FicheCareco findByCode(String code) throws FinderException {
		return dao.findByCode(code);
	}

	//@RolesAllowed("admin")
	public List<FicheCareco> selectAll() {
		return dao.selectAll();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										DTO
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<FicheCarecoDTO> findWithNamedQueryDTO(String queryName) throws FinderException {
		return null;
	}

	@Override
	public List<FicheCarecoDTO> findWithJPQLQueryDTO(String JPQLquery) throws FinderException {
		List<FicheCareco> entityList = dao.findWithJPQLQuery(JPQLquery);
		List<FicheCarecoDTO> l = null;
		if(entityList!=null) {
			l = listEntityToListDTO(entityList);
		}
		return l;
	}

	public FicheCarecoDTO entityToDTO(FicheCareco entity) {
		//		FicheCarecoDTO dto = new FicheCarecoDTO();
		//		dto.setId(entity.getIdTCivilite());
		//		dto.setCodeTCivilite(entity.getCodeTCivilite());
		//		return dto;
		FicheCarecoMapper mapper = new FicheCarecoMapper();
		return mapper.mapEntityToDto(entity, null);
	}

	public List<FicheCarecoDTO> listEntityToListDTO(List<FicheCareco> entity) {
		List<FicheCarecoDTO> l = new ArrayList<FicheCarecoDTO>();

		for (FicheCareco FicheCareco : entity) {
			l.add(entityToDTO(FicheCareco));
		}

		return l;
	}

	@RolesAllowed("admin")
	public List<FicheCarecoDTO> selectAllDTO() {
		System.out.println("List of FicheCarecoDTO EJB :");
		ArrayList<FicheCarecoDTO> liste = new ArrayList<FicheCarecoDTO>();

		List<FicheCareco> projects = selectAll();
		for(FicheCareco project : projects) {
			liste.add(entityToDTO(project));
		}

		return liste;
	}

	public FicheCarecoDTO findByIdDTO(int id) throws FinderException {
		return entityToDTO(findById(id));
	}

	public FicheCarecoDTO findByCodeDTO(String code) throws FinderException {
		return entityToDTO(findByCode(code));
	}

	@Override
	public void error(FicheCarecoDTO dto) {
		throw new EJBException("Test erreur EJB");
	}

	@Override
	public int selectCount() {
		return dao.selectAll().size();
		//return 0;
	}

	@Override
	public void mergeDTO(FicheCarecoDTO dto) throws EJBException {
		try {
			FicheCarecoMapper mapper = new FicheCarecoMapper();
			FicheCareco entity = null;
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
	public void persistDTO(FicheCarecoDTO dto) throws CreateException {
		try {
			FicheCarecoMapper mapper = new FicheCarecoMapper();
			FicheCareco entity = mapper.mapDtoToEntity(dto,null);
			//dao.persist(entity);
			enregistrerPersist(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CreateException(e.getMessage());
		}
	}

	@Override
	public void removeDTO(FicheCarecoDTO dto) throws RemoveException {
		try {
			FicheCarecoMapper mapper = new FicheCarecoMapper();
			FicheCareco entity = mapper.mapDtoToEntity(dto,null);
			//dao.remove(entity);
			supprimer(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}
	}

	@Override
	protected FicheCareco refresh(FicheCareco persistentInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateEntity(FicheCareco value) /*throws ExceptLgr*/ {
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
	public void validateEntityProperty(FicheCareco value, String propertyName) {
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
	public void validateDTO(FicheCarecoDTO dto) {
		try {
			FicheCarecoMapper mapper = new FicheCarecoMapper();
			FicheCareco entity = mapper.mapDtoToEntity(dto,null);
			validateEntity(entity);

			//validation automatique via la JSR bean validation
			//			BeanValidator<FicheCarecoDTO> validator = new BeanValidator<FicheCarecoDTO>(FicheCarecoDTO.class);
			//			validator.validate(dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void validateDTOProperty(FicheCarecoDTO dto, String propertyName) {
		try {
			FicheCarecoMapper mapper = new FicheCarecoMapper();
			FicheCareco entity = mapper.mapDtoToEntity(dto,null);
			validateEntityProperty(entity,propertyName);

			//validation automatique via la JSR bean validation
			//			BeanValidator<FicheCarecoDTO> validator = new BeanValidator<FicheCarecoDTO>(FicheCarecoDTO.class);
			//			validator.validateField(dto,propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}

	}

}
