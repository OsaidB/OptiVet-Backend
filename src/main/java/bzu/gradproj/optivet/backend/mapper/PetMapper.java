package bzu.gradproj.optivet.backend.mapper;

import bzu.gradproj.optivet.backend.dto.PetDTO;
import bzu.gradproj.optivet.backend.model.entity.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PetMapper {

    PetMapper INSTANCE = Mappers.getMapper(PetMapper.class);

    @Mapping(source = "owner.id", target = "ownerId")
    PetDTO toPetDTO(Pet pet);

    @Mapping(source = "ownerId", target = "owner.id")
    Pet toPetEntity(PetDTO petDTO);
}
