package fr.legrain.bdg.tiers.service.remote;

//@Remote
public interface IAbstractLgrDAOServerDTO<DTO> {

	public void enregistrerPersistDTO(DTO transientInstance) throws Exception;

	public void enregistrerMergeDTO(DTO persistentInstance) throws Exception;
	
	//public Boolean autoriseModificationDTO(DTO persistentInstance);

	//public void messageNonAutoriseModificationDTO() throws Exception;

	public void verifAutoriseModificationDTO(DTO persistentInstance) throws Exception;

	public void modifierDTO(DTO persistentInstance) throws Exception;

	//public void insererDTO(DTO transientInstance) throws Exception;

	public void annulerDTO(DTO persistentInstance) throws Exception;

	public DTO annulerTDTO(DTO persistentInstance) throws Exception;

	public void supprimerDTO(DTO persistentInstance) throws Exception;

}