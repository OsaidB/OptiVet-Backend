package bzu.gradproj.optivet.backend.mapper;

import bzu.gradproj.optivet.backend.dto.ProjectMemberDTO;
import bzu.gradproj.optivet.backend.model.entity.Project;
import bzu.gradproj.optivet.backend.model.entity.ProjectMember;
import bzu.gradproj.optivet.backend.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {

    ProjectMemberMapper INSTANCE = Mappers.getMapper(ProjectMemberMapper.class);


    @Mappings({
            @Mapping(source = "project.projectId", target = "projectId"),
            @Mapping(source = "user.id", target = "userId", nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL)

    })
    ProjectMemberDTO toProjectMemberDTO(ProjectMember projectMember);

    @Mappings({
            @Mapping(source = "projectId", target = "project.projectId"),
            @Mapping(source = "userId", target = "user.id", nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL)

    })
    ProjectMember toProjectMemberEntity(ProjectMemberDTO projectMemberDTO);
}
