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

    @NotNull(message = "Client ID cannot be null")
    private Long clientId;

    @NotNull(message = "Pet ID cannot be null")
    private Long petId;

    @NotNull(message = "Appointment date cannot be null")
    private LocalDateTime appointmentDate;

    private String status; // Use enum string representation like "SCHEDULED", "COMPLETED", etc.

    @NotNull(message = "Vet ID cannot be null")
    private Long vetId;  // New field to link the appointment with a vet

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
