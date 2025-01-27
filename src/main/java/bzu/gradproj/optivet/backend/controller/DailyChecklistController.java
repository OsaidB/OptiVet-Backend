package bzu.gradproj.optivet.backend.controller;

import bzu.gradproj.optivet.backend.dto.DailyChecklistDTO;
import bzu.gradproj.optivet.backend.dto.PetDTO;
import bzu.gradproj.optivet.backend.service.DailyChecklistService;
import bzu.gradproj.optivet.backend.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/daily-checklists")
@RequiredArgsConstructor
public class DailyChecklistController {

    private final DailyChecklistService dailyChecklistService;
    private final PetService petService;

    // Create a new Daily Checklist
    @PostMapping
    public ResponseEntity<DailyChecklistDTO> createDailyChecklist(@RequestBody DailyChecklistDTO dailyChecklistDTO) {
        DailyChecklistDTO createdChecklist = dailyChecklistService.createDailyChecklist(dailyChecklistDTO);
        return new ResponseEntity<>(createdChecklist, HttpStatus.CREATED);
    }



/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Get a Daily Checklist by ID
    @GetMapping("/{id}")
    public ResponseEntity<DailyChecklistDTO> getDailyChecklistById(@PathVariable Long id) {
        DailyChecklistDTO dailyChecklistDTO = dailyChecklistService.getDailyChecklistById(id);
        return ResponseEntity.ok(dailyChecklistDTO);
    }

    // Get all Daily Checklists for a specific Pet
    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<DailyChecklistDTO>> getDailyChecklistsByPetId(@PathVariable Long petId) {
        List<DailyChecklistDTO> dailyChecklists = dailyChecklistService.getDailyChecklistsByPetId(petId);
        return ResponseEntity.ok(dailyChecklists);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Get all Daily Checklists
    @GetMapping
    public ResponseEntity<List<DailyChecklistDTO>> getAllDailyChecklists() {
        List<DailyChecklistDTO> dailyChecklists = dailyChecklistService.getAllDailyChecklists();
        return ResponseEntity.ok(dailyChecklists);
    }

    // Update an existing Daily Checklist by ID
    @PutMapping("/{id}")
    public ResponseEntity<DailyChecklistDTO> updateDailyChecklist(
            @PathVariable Long id,
            @RequestBody DailyChecklistDTO dailyChecklistDTO) {
        DailyChecklistDTO updatedChecklist = dailyChecklistService.updateDailyChecklist(id, dailyChecklistDTO);
        return ResponseEntity.ok(updatedChecklist);
    }

    // Delete a Daily Checklist by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDailyChecklist(@PathVariable Long id) {
        dailyChecklistService.deleteDailyChecklist(id);
        return ResponseEntity.noContent().build();
    }

    // Add this endpoint to fetch checklists with critical issues
    @GetMapping("/critical")
    public ResponseEntity<List<DailyChecklistDTO>> getDailyChecklistsWithCriticalIssues() {
        List<DailyChecklistDTO> criticalChecklists = dailyChecklistService.getDailyChecklistsWithCriticalIssues();
        return ResponseEntity.ok(criticalChecklists);
    }

    // Fetch pets that have completed their daily checklist for a specific date
    @GetMapping("/checked-pets")
    public ResponseEntity<List<PetDTO>> getCheckedPets(@RequestParam("date") String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        List<Long> checkedPetIds = dailyChecklistService.getCheckedPetIdsForToday(parsedDate);
        List<PetDTO> checkedPets = dailyChecklistService.getPetsByIds(checkedPetIds);
        return ResponseEntity.ok(checkedPets);
    }

    // Fetch pets that have not completed their daily checklist for a specific date
    @GetMapping("/unchecked-pets")
    public ResponseEntity<List<PetDTO>> getUncheckedPets(@RequestParam("date") String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        List<Long> uncheckedPetIds = dailyChecklistService.getUncheckedPetIdsForToday(parsedDate);
        List<PetDTO> uncheckedPets = dailyChecklistService.getPetsByIds(uncheckedPetIds);
        return ResponseEntity.ok(uncheckedPets);
    }



}
