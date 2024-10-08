package bzu.gradproj.optivet.backend.controller;

import bzu.gradproj.optivet.backend.dto.PetDTO;
import bzu.gradproj.optivet.backend.service.PetService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/pets")
public class PetController {

    @Autowired
    private final PetService petService;

    @PostMapping
    public ResponseEntity<PetDTO> createPet(@Valid @RequestBody PetDTO petDTO) {
        PetDTO createdPet = petService.createPet(petDTO);
        return new ResponseEntity<>(createdPet, HttpStatus.CREATED);
    }

    @GetMapping("{petId}")
    public ResponseEntity<PetDTO> getPetById(@PathVariable("petId") Long petId) {
        PetDTO petDTO = petService.getPetById(petId);
        return ResponseEntity.ok(petDTO);
    }

    @GetMapping
    public ResponseEntity<List<PetDTO>> getAllPets() {
        List<PetDTO> pets = petService.getAllPets();
        return ResponseEntity.ok(pets);
    }

    @GetMapping("owner/{ownerId}")
    public ResponseEntity<List<PetDTO>> getPetsByOwnerId(@PathVariable("ownerId") Long ownerId) {
        List<PetDTO> pets = petService.getPetsByOwnerId(ownerId);
        return ResponseEntity.ok(pets);
    }

    @PutMapping("{petId}")
    public ResponseEntity<PetDTO> updatePet(@PathVariable("petId") Long petId,
                                            @Valid @RequestBody PetDTO updatedPet) {
        PetDTO petDTO = petService.updatePet(petId, updatedPet);
        return ResponseEntity.ok(petDTO);
    }

    @DeleteMapping("{petId}")
    public ResponseEntity<String> deletePet(@PathVariable("petId") Long petId) {
        petService.deletePet(petId);
        return ResponseEntity.ok("Pet deleted successfully.");
    }
}
