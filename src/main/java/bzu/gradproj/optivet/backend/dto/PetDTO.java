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
public class PetDTO {

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

    private MedicalHistoryDTO medicalHistoryy;

    private String imageUrl; // New field to store the image URL

    // New field for residency type
    private ResidencyType residencyType;
}
