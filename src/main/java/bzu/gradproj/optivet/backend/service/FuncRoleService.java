package bzu.gradproj.optivet.backend.service;

import bzu.gradproj.optivet.backend.dto.RoleDTO;

import java.util.List;

public interface FuncRoleService {
    RoleDTO createRole(RoleDTO roleDTO);
    RoleDTO getRoleById(Long roleId);
    List<RoleDTO> getAllRoles();
    void deleteRole(Long roleId);
}
