package bzu.gradproj.optivet.backend.mapper;

import bzu.gradproj.optivet.backend.dto.MedicalHistoryDTO;

import bzu.gradproj.optivet.backend.model.entity.MedicalHistory;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MedicalHistoryMapper {

    PetMapper INSTANCE = Mappers.getMapper(PetMapper.class);

    @Mappings({
            @Mapping(source = "pet.id", target = "id"),
//            @Mapping(source = "surgeons", target = "surgeons")

    })
    MedicalHistoryDTO toDTO(MedicalHistory medicalHistory);


    @Mappings({

            @Mapping(source = "id", target = "pet.id"),
//            @Mapping(source = "surgeons", target = "surgeons")
    })
    MedicalHistory toEntity(MedicalHistoryDTO medicalHistoryDTO);
}
