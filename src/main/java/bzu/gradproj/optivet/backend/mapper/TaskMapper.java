package bzu.gradproj.optivet.backend.mapper;

import bzu.gradproj.optivet.backend.dto.TaskDTO;
import bzu.gradproj.optivet.backend.model.entity.Task;
import bzu.gradproj.optivet.backend.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mappings({
            @Mapping(source = "assignedTo.id", target = "assignedToUserId", nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL),
            @Mapping(source = "board.boardId", target = "boardId")
    })
    TaskDTO toTaskDTO(Task task);

    @Mappings({
            @Mapping(source = "assignedToUserId", target = "assignedTo.id"),
            @Mapping(source = "boardId", target = "board.boardId")
    })
    Task toTaskEntity(TaskDTO taskDTO);


    default Long mapUserToId(User user) {
        return user == null ? null : user.getId();
    }

    default User mapIdToUser(Long userId) {
        if (userId == null) {
            return null;
        }
        User user = new User();
        user.setId(userId);
        return user;
    }

}
