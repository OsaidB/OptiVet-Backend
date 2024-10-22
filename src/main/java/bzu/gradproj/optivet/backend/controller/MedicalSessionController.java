package bzu.gradproj.optivet.backend.controller;

import bzu.gradproj.optivet.backend.dto.MedicalSessionDTO;
import bzu.gradproj.optivet.backend.service.MedicalSessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/medical-sessions")
public class MedicalSessionController {

    private final MedicalSessionService medicalSessionService;

    // Update the endpoint to take veterinarianId from the URL
    @PostMapping("/veterinarian/{veterinarianId}")
    public ResponseEntity<MedicalSessionDTO> createSession(@PathVariable Long veterinarianId,
                                                           @RequestBody MedicalSessionDTO medicalSessionDTO) {
        // Pass the veterinarianId to the service along with the DTO
        MedicalSessionDTO createdSession = medicalSessionService.createSession(medicalSessionDTO, veterinarianId);
        return ResponseEntity.ok(createdSession);
    }

    @PutMapping("{sessionId}")
    public ResponseEntity<MedicalSessionDTO> updateSession(@PathVariable Long sessionId, @RequestBody MedicalSessionDTO medicalSessionDTO) {
        MedicalSessionDTO updatedSession = medicalSessionService.updateSession(sessionId, medicalSessionDTO);
        return ResponseEntity.ok(updatedSession);
    }

    @DeleteMapping("{sessionId}")
    public ResponseEntity<String> deleteSession(@PathVariable Long sessionId) {
        medicalSessionService.deleteSession(sessionId);
        return ResponseEntity.ok("Medical session deleted successfully.");
    }

    @GetMapping("{sessionId}")
    public ResponseEntity<MedicalSessionDTO> getSessionById(@PathVariable Long sessionId) {
        MedicalSessionDTO sessionDTO = medicalSessionService.getSessionById(sessionId);
        return ResponseEntity.ok(sessionDTO);
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<MedicalSessionDTO>> getSessionsByPetId(@PathVariable Long petId) {
        List<MedicalSessionDTO> sessions = medicalSessionService.getSessionsByPetId(petId);
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<MedicalSessionDTO>> getSessionsByOwnerId(@PathVariable Long ownerId) {
        List<MedicalSessionDTO> sessions = medicalSessionService.getSessionsByOwnerId(ownerId);
        return ResponseEntity.ok(sessions);
    }
}
