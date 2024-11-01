package bzu.gradproj.optivet.backend.mapper;

import bzu.gradproj.optivet.backend.dto.AllergiesDTO;

import bzu.gradproj.optivet.backend.model.entity.Allergies;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AllergiesMapper {

    @Mapping(source = "medicalHistory.id", target = "medicalHistoryId")
    AllergiesDTO toDTO(Allergies allergy);


    @Mapping(source = "medicalHistoryId", target = "medicalHistory.id")

    Allergies toEntity(AllergiesDTO allergyDTO);
}