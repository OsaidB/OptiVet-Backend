package bzu.gradproj.optivet.backend.mapper;

import bzu.gradproj.optivet.backend.dto.ProjectDTO;
import bzu.gradproj.optivet.backend.model.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);
    ProjectDTO toProjectDTO(Project project);
    Project toProjectEntity(ProjectDTO projectDTO);
}
