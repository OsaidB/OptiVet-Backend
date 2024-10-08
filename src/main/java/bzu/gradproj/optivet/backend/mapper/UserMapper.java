/*
let's create a User mapper class
to map User entity to UserDTO, and UserDTO to User entity.

so whenever we build the rest Services, then we have to convert UserDTO into User JPA entity, and User JPA entity into UserDTO!
-> so instead of writing the same logic in all the classes,
-> let's create a mapper class, and let's keep the common logic
*/
package bzu.gradproj.optivet.backend.mapper;

import bzu.gradproj.optivet.backend.dto.UserDTO;
import bzu.gradproj.optivet.backend.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "id", target = "userId")
    @Mapping(source = "funcRole.funcRoleId", target = "functionalRoleId")
    UserDTO toUserDTO(User user);

    @Mapping(source = "userId", target = "id")
    @Mapping(source = "functionalRoleId", target = "funcRole.funcRoleId") // Assuming you need to set the id on funcRole
    @Mapping(target = "funcRole", ignore = true) // The role will be set in the service
    User toUserEntity(UserDTO userDTO);
}
