package bzu.gradproj.optivet.backend.controller;

import bzu.gradproj.optivet.backend.dto.AllergiesDTO;
import bzu.gradproj.optivet.backend.dto.AppointmentDTO;
import bzu.gradproj.optivet.backend.dto.ChronicConditionsDTO;
import bzu.gradproj.optivet.backend.service.AllergiesService;
import bzu.gradproj.optivet.backend.service.AppointmentService;
import bzu.gradproj.optivet.backend.service.ChronicConditionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/medicalHistories/chronicConditions")
@RequiredArgsConstructor
public class ChronicConditionsController {

    private final ChronicConditionsService chronicConditionsService;

    @PostMapping("/{petId}")
    public ResponseEntity<ChronicConditionsDTO> createChronicCondition(@RequestBody ChronicConditionsDTO chronicConditionsDTO, @PathVariable Long petId) {
        ChronicConditionsDTO createdChronicCondition = chronicConditionsService.createChronicCondition(chronicConditionsDTO,petId);
        return ResponseEntity.ok(createdChronicCondition);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChronicCondition(@PathVariable Long id) {
        chronicConditionsService.deleteChronicCondition(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{petId}")
    public ResponseEntity<List<ChronicConditionsDTO>> getChronicConditionsByPetId(@PathVariable Long petId) {
        List<ChronicConditionsDTO> chronicConditions = chronicConditionsService.getChronicConditionsByPetId(petId);
        return ResponseEntity.ok(chronicConditions);
    }


}
