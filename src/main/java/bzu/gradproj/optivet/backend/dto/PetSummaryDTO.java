package bzu.gradproj.optivet.backend.dto;

import bzu.gradproj.optivet.backend.model.entity.Pet.ResidencyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PetSummaryDTO {

    private Long id;

    @NotNull(message = "Pet name cannot be null")
    private String name;

    @NotNull(message = "Pet type cannot be null")
    private String type;

    private String breed;
    private LocalDate birthDate;
    private String medicalHistory;

    @NotNull(message = "Owner ID cannot be null")
    private Long ownerId;

    private String imageUrl; // Field to store the image URL

    // Field for residency type
    private ResidencyType residencyType;

    // Additional fields
    private boolean deleted; // Indicates if the pet is deleted
    private String manualId;   // Field for manual ID
    private String gender;     // Field for pet gender
}
