package bzu.gradproj.optivet.backend.controller;

import bzu.gradproj.optivet.backend.dto.AllergiesDTO;
import bzu.gradproj.optivet.backend.dto.AppointmentDTO;
import bzu.gradproj.optivet.backend.dto.SurgeoriesDTO;
import bzu.gradproj.optivet.backend.service.AllergiesService;
import bzu.gradproj.optivet.backend.service.AppointmentService;
import bzu.gradproj.optivet.backend.service.SurgeoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/medicalHistories/surgeories")
@RequiredArgsConstructor
public class SurgeoriesController {

    private final SurgeoriesService surgeoriesService;

    @PostMapping("/{petId}")
    public ResponseEntity<SurgeoriesDTO> createSurgeory(@RequestBody SurgeoriesDTO surgeoriesDTO, @PathVariable Long petId) {
        SurgeoriesDTO createdSurgeory = surgeoriesService.createSurgeory(surgeoriesDTO,petId);
        return ResponseEntity.ok(createdSurgeory);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurgeory(@PathVariable Long id) {
        surgeoriesService.deleteSurgeory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{petId}")
    public ResponseEntity<List<SurgeoriesDTO>> getSurgeoriesByPetId(@PathVariable Long petId) {
        List<SurgeoriesDTO> surgeories = surgeoriesService.getSurgeoriesByPetId(petId);
        return ResponseEntity.ok(surgeories);
    }


}
