package bzu.gradproj.optivet.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistoryDTO {

    private Long id;

    private String dietaryPreferences;

    private String notes;

    private List<AllergiesDTO> allergies;
    private List<ChronicConditionsDTO> chronicConditions;
    private List<SurgeoriesDTO> surgeories;
    private List<VaccinationsDTO> vaccinations;

}