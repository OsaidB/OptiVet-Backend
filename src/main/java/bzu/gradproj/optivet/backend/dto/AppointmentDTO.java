package bzu.gradproj.optivet.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {

    private Long id;

    // ClientId can be null when creating a new available slot
    private Long clientId;

    // PetId can be null when creating a new available slot
    private Long petId;

    @NotNull(message = "Appointment date cannot be null")
    private LocalDateTime appointmentDate;

    @NotNull(message = "Status cannot be null")
    private String status = "AVAILABLE";  // Default status: AVAILABLE

    @NotNull(message = "Vet ID cannot be null")
    private Long vetId;  // Must have a vet associated
}
