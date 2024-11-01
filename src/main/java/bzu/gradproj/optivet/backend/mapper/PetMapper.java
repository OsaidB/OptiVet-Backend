package bzu.gradproj.optivet.backend.mapper;

import bzu.gradproj.optivet.backend.dto.PetDTO;
import bzu.gradproj.optivet.backend.model.entity.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PetMapper {

    PetMapper INSTANCE = Mappers.getMapper(PetMapper.class);

    @Mappings({
            @Mapping(source = "owner.id", target = "ownerId"),
//            @Mapping(source = "medicalHistoryy.id", target = "medicalHistoryy")
    })
    PetDTO toDTO(Pet pet);


    @Mappings({
            @Mapping(source = "ownerId", target = "owner.id"),
//            @Mapping(source = "medicalHistoryy", target = "medicalHistoryy.id")
    })
    Pet toEntity(PetDTO petDTO);
}
