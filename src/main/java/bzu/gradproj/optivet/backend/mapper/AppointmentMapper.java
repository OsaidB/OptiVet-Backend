package bzu.gradproj.optivet.backend.mapper;

import bzu.gradproj.optivet.backend.dto.AppointmentDTO;
import bzu.gradproj.optivet.backend.model.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

    @Mappings({
            @Mapping(source = "client.id", target = "clientId"),
            @Mapping(source = "pet.id", target = "petId"),
            @Mapping(source = "vet.id", target = "vetId") // Mapping vet entity to vetId
    })
    AppointmentDTO toDTO(Appointment appointment);

    @Mappings({
            @Mapping(source = "clientId", target = "client.id"),
            @Mapping(source = "petId", target = "pet.id"),
            @Mapping(source = "vetId", target = "vet.id") // Mapping vetId to vet entity
    })
    Appointment toEntity(AppointmentDTO appointmentDTO);
}

