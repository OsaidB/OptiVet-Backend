package bzu.gradproj.optivet.backend.controller;

import bzu.gradproj.optivet.backend.dto.AllergiesDTO;
import bzu.gradproj.optivet.backend.dto.AppointmentDTO;
import bzu.gradproj.optivet.backend.dto.MedicalHistoryDTO;
import bzu.gradproj.optivet.backend.service.AllergiesService;
import bzu.gradproj.optivet.backend.service.AppointmentService;
import bzu.gradproj.optivet.backend.service.MedicalHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/medicalHistories")
@RequiredArgsConstructor
public class MedicalHistoryController {

    private final MedicalHistoryService medicalHistoryService;



//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteMedicalHistory(@PathVariable Long id) {
//        medicalHistoryService.deleteMedicalHistory(id);
//        return ResponseEntity.noContent().build();
//    }



    @PutMapping("/{petId}")
    public ResponseEntity<MedicalHistoryDTO> updateMedicalHistory(@RequestBody MedicalHistoryDTO medicalHistoryDTO, @PathVariable Long petId) {
        MedicalHistoryDTO updatedMedicalHistory = medicalHistoryService.updateMedicalHistory(medicalHistoryDTO,petId);
        return ResponseEntity.ok(updatedMedicalHistory);
    }


    @GetMapping("/{petId}")
    public ResponseEntity<MedicalHistoryDTO> getMedicalHistory(@PathVariable Long petId) {
        MedicalHistoryDTO updatedMedicalHistory = medicalHistoryService.getMedicalHistory(petId);
        return ResponseEntity.ok(updatedMedicalHistory);
    }

}
