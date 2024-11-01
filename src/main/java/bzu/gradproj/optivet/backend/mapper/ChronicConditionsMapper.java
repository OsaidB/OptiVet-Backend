package bzu.gradproj.optivet.backend.mapper;

import bzu.gradproj.optivet.backend.dto.ChronicConditionsDTO;
import bzu.gradproj.optivet.backend.model.entity.ChronicConditions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChronicConditionsMapper {

    @Mapping(source = "medicalHistory.id", target = "medicalHistoryId")
    ChronicConditionsDTO toDTO(ChronicConditions chronicCondition);


    @Mapping(source = "medicalHistoryId", target = "medicalHistory.id")

    ChronicConditions toEntity(ChronicConditionsDTO chronicConditionDTO);
}