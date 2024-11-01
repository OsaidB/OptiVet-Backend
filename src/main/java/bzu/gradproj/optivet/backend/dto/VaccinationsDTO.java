package bzu.gradproj.optivet.backend.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationsDTO {

    private Long id;

    private String vaccination;

    @NotNull(message = "Medical_history ID cannot be null")
    private Long medicalHistoryId;

}