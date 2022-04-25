package fr.legrain.tiers.dao.jpa;

import java.util.Date;

public interface ILgrEntity{
	
	public Date getQuanCree();
	public Date getQuandModif();
	public Integer getQuiCree();
	public Integer getQuiModif();

//	public String getTableOrigine();
//	public Integer getIdOrigine();

	public String getIp();

	public Integer getVersionObj();

	public void setQuanCree(Date quanCree);
	public void setQuandModif(Date quandModif);
	public void setQuiCree(Integer quiCree);
	public void setQuiModif(Integer quiModif);

//	public void setTableOrigine(String tableOrigine);
//	public void setIdOrigine(Integer idOrigine);

	public void setIp(String ip);
	public void setVersionObj(Integer versionObj);

}
