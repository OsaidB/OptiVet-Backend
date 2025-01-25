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


    @Mapping(source = "medicalHistoryImageUrls", target = "medicalHistoryImageUrls")
    @Mapping(source = "pet.id", target = "petId")
    MedicalHistoryDTO toDTO(MedicalHistory medicalHistory);


    @Mapping(source = "medicalHistoryImageUrls", target = "medicalHistoryImageUrls")
    @Mapping(source = "petId", target = "pet.id")
    MedicalHistory toEntity(MedicalHistoryDTO medicalHistoryDTO);
}
