package bzu.gradproj.optivet.backend.controller;

import bzu.gradproj.optivet.backend.dto.AllergiesDTO;
import bzu.gradproj.optivet.backend.dto.AppointmentDTO;
import bzu.gradproj.optivet.backend.service.AllergiesService;
import bzu.gradproj.optivet.backend.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/medicalHistories/allergies")
@RequiredArgsConstructor
public class AllergiesController {

    private final AllergiesService allergiesService;

    @PostMapping("/{petId}")
    public ResponseEntity<AllergiesDTO> createAllergy(@RequestBody AllergiesDTO allergiesDTO, @PathVariable Long petId) {
        AllergiesDTO createdAllergy = allergiesService.createAllergy(allergiesDTO,petId);
        return ResponseEntity.ok(createdAllergy);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAllergy(@PathVariable Long id) {
        allergiesService.deleteAllergy(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{petId}")
    public ResponseEntity<List<AllergiesDTO>> getAllergiesByPetId(@PathVariable Long petId) {
        List<AllergiesDTO> allergies = allergiesService.getAllergiesByPetId(petId);
        return ResponseEntity.ok(allergies);
    }


}
