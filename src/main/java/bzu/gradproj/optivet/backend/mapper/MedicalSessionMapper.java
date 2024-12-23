package bzu.gradproj.optivet.backend.mapper;

import bzu.gradproj.optivet.backend.dto.MedicalSessionDTO;
import bzu.gradproj.optivet.backend.model.entity.MedicalSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MedicalSessionMapper {

    @Mapping(source = "pet.id", target = "petId")
    @Mapping(source = "owner.id", target = "ownerId")
    @Mapping(source = "veterinarian.id", target = "veterinarianId")
    @Mapping(target = "testResultsImageUrls", source = "testResultsImageUrls")
    MedicalSessionDTO toDTO(MedicalSession medicalSession);

    @Mapping(source = "petId", target = "pet.id")
    @Mapping(source = "ownerId", target = "owner.id")
    @Mapping(source = "veterinarianId", target = "veterinarian.id")
    @Mapping(target = "testResultsImageUrls", source = "testResultsImageUrls")

    MedicalSession toEntity(MedicalSessionDTO medicalSessionDTO);
}
