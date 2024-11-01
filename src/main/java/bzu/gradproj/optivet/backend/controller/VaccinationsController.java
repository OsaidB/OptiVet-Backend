package bzu.gradproj.optivet.backend.controller;

import bzu.gradproj.optivet.backend.dto.AllergiesDTO;
import bzu.gradproj.optivet.backend.dto.AppointmentDTO;
import bzu.gradproj.optivet.backend.dto.VaccinationsDTO;
import bzu.gradproj.optivet.backend.service.AllergiesService;
import bzu.gradproj.optivet.backend.service.AppointmentService;
import bzu.gradproj.optivet.backend.service.VaccinationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/medicalHistories/vaccinations")
@RequiredArgsConstructor
public class VaccinationsController {

    private final VaccinationsService vaccinationsService;

    @PostMapping("/{petId}")
    public ResponseEntity<VaccinationsDTO> createVaccination(@RequestBody VaccinationsDTO vaccinationsDTO, @PathVariable Long petId) {
        VaccinationsDTO createdVaccination = vaccinationsService.createVaccination(vaccinationsDTO,petId);
        return ResponseEntity.ok(createdVaccination);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVaccination(@PathVariable Long id) {
        vaccinationsService.deleteVaccination(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{petId}")
    public ResponseEntity<List<VaccinationsDTO>> getVaccinationsByPetId(@PathVariable Long petId) {
        List<VaccinationsDTO> vaccinations = vaccinationsService.getVaccinationsByPetId(petId);
        return ResponseEntity.ok(vaccinations);
    }


}
