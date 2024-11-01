package bzu.gradproj.optivet.backend.mapper;

import bzu.gradproj.optivet.backend.dto.VaccinationsDTO;
import bzu.gradproj.optivet.backend.model.entity.Vaccinations;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VaccinationsMapper {

    @Mapping(source = "medicalHistory.id", target = "medicalHistoryId")
    VaccinationsDTO toDTO(Vaccinations vaccination);


    @Mapping(source = "medicalHistoryId", target = "medicalHistory.id")

    Vaccinations toEntity(VaccinationsDTO vaccinationDTO);
}