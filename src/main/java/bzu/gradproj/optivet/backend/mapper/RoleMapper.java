package bzu.gradproj.optivet.backend.mapper;

import bzu.gradproj.optivet.backend.dto.RoleDTO;
import bzu.gradproj.optivet.backend.model.entity.FunctionalRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO toRoleDTO(FunctionalRole role);
    FunctionalRole toRoleEntity(RoleDTO roleDTO);
}
