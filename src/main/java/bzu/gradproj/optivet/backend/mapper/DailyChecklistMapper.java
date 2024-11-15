package bzu.gradproj.optivet.backend.mapper;

import bzu.gradproj.optivet.backend.dto.DailyChecklistDTO;
import bzu.gradproj.optivet.backend.model.entity.DailyChecklist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DailyChecklistMapper {

    @Mapping(source = "pet.id", target = "petId")
    DailyChecklistDTO toDTO(DailyChecklist dailyChecklist);

    @Mapping(source = "petId", target = "pet.id")
    DailyChecklist toEntity(DailyChecklistDTO dailyChecklistDTO);

    // New method to update an existing entity from a DTO
    @Mapping(source = "petId", target = "pet.id")
    void updateFromDto(DailyChecklistDTO dailyChecklistDTO, @MappingTarget DailyChecklist dailyChecklist);
}
