package bzu.gradproj.optivet.backend.dto;

//import bzu.gradproj.optivet.backend.model.entity.ProductCategory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PetForAdoptionDTO {

    private Long id;

    @NotNull(message = "Pet name cannot be null")
    private String name;


    private LocalDate birthDate;

    @NotNull(message = "Pet breed cannot be null")
    private String type;


    private String breed;


    @NotNull(message = "Pet url cannot be null")
    private String petForAdoptionImageUrl;


    private String petForAdoptionDescription;


    //private List<String> medicalHistoryImages;


}