package bzu.gradproj.optivet.backend.mapper;

import bzu.gradproj.optivet.backend.dto.AllergiesDTO;

import bzu.gradproj.optivet.backend.dto.SurgeoriesDTO;
import bzu.gradproj.optivet.backend.model.entity.Allergies;
import bzu.gradproj.optivet.backend.model.entity.Surgeories;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SurgeoriesMapper {

    @Mapping(source = "medicalHistory.id", target = "medicalHistoryId")
    SurgeoriesDTO toDTO(Surgeories surgeory);


    @Mapping(source = "medicalHistoryId", target = "medicalHistory.id")

    Surgeories toEntity(SurgeoriesDTO surgeoryDTO);
}