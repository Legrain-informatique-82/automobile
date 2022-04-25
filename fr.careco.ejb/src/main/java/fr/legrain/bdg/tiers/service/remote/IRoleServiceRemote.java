package fr.legrain.bdg.tiers.service.remote;

import java.util.List;

import javax.ejb.Remote;

import fr.legrain.tiers.dto.RoleDTO;
import fr.legrain.tiers.model.Role;

public interface IRoleServiceRemote extends IGenericCRUDServiceRemote<Role,RoleDTO>,
														IAbstractLgrDAOServer<Role>,IAbstractLgrDAOServerDTO<RoleDTO>{
}
