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
            @Mapping(source = "deleted", target = "deleted"),
            @Mapping(source = "manualId", target = "manualId"),
            @Mapping(source = "gender", target = "gender")
    })
    PetDTO toDTO(Pet pet);


    @Mappings({
            @Mapping(source = "ownerId", target = "owner.id"),
//            @Mapping(source = "medicalHistoryy", target = "medicalHistoryy.id")
            @Mapping(source = "deleted", target = "deleted"),
            @Mapping(source = "manualId", target = "manualId"),
            @Mapping(source = "gender", target = "gender")
    })
    Pet toEntity(PetDTO petDTO);
}
