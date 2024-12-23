package bzu.gradproj.optivet.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalSessionDTO {

    private Long id;

    // Medical Details
    private String diagnosis;
    private String treatment;

    @NotNull(message = "Session date cannot be null")
    private LocalDateTime sessionDate;

    @NotNull(message = "Pet ID cannot be null")
    private Long petId;

    @NotNull(message = "Owner ID cannot be null")
    private Long ownerId;

    @NotNull(message = "Veterinarian ID cannot be null")
    private Long veterinarianId;

    private String symptoms;
    private String treatmentPlan;
    private String medicationsPrescribed;

    // Vital Signs
    private Double weight;
    private Double temperature;
    private Integer heartRate;

    // Session Notes
    private String veterinarianNotes;

    // Lab Testing
    private String testsOrdered;
    private List<String> testResultsImageUrls; // Updated to handle multiple image URLs

    // Follow-up Actions
    private LocalDate nextAppointmentDate;
    private String postTreatmentInstructions;
}
