package bzu.gradproj.optivet.backend.dto;

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
public class DailyChecklistDTO {

    private Long id;

    @NotNull(message = "Date cannot be null")
    private LocalDate date;

    @NotNull(message = "Pet ID cannot be null")
    private Long petId;

    // Health Checks
    private Boolean eatingWell;
    private Boolean drinkingWater;
    private Boolean activeBehavior;
    private Boolean normalVitalSigns;

    // Observations
    private String healthObservations;
    private String weightChange;
    private String injuriesOrWounds;

    // Maintenance
    private Boolean feedingCompleted;
    private Boolean cleanedLivingSpace;

    // Poop Check
    private Boolean poopNormal;
    private String poopNotes;

    // Critical Notes
    private Boolean criticalIssueFlag;
    private String criticalNotes;
}
