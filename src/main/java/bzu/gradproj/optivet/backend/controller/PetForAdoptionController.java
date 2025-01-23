package bzu.gradproj.optivet.backend.controller;

import bzu.gradproj.optivet.backend.dto.*;
import bzu.gradproj.optivet.backend.service.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/petsForAdoption")
@RequiredArgsConstructor
public class PetForAdoptionController {

    private final PetForAdoptionService petForAdoptionService;
    private static final String UPLOAD_DIR = "uploads/";

    @PostMapping
    public ResponseEntity<PetForAdoptionDTO> createPetForAdoption(@RequestBody PetForAdoptionDTO petForAdoptionDTO) {
        PetForAdoptionDTO createdPetForAdoption = petForAdoptionService.createPetForAdoption(petForAdoptionDTO);
        return ResponseEntity.ok(createdPetForAdoption);
    }



    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadPetForAdoptionImages(@RequestParam("image") MultipartFile file) {



        try {



            if ((file.isEmpty())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no file selected");
            }

            // Generate unique file name
            String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);

            // Ensure upload directory exists
            Files.createDirectories(filePath.getParent());

            // Save the file to the upload directory
            Files.write(filePath, file.getBytes());

            // Construct the URL to access the image
            String imageUrl = "/uploads/" + fileName;





            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");

        }

    }



    @GetMapping("/uploads/{fileName}")
    public ResponseEntity<Resource> serveImage(@PathVariable String fileName) {
        try {
            // Define the path to the file in the upload directory
            Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            // Determine the content type of the image
            String contentType = "image/jpeg"; // Adjust based on the image type, if necessary

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }





    @PutMapping("/{petForAdoptionId}")
    public ResponseEntity<PetForAdoptionDTO> updatePetForAdoption(@RequestBody PetForAdoptionDTO petForAdoptionDTO, @PathVariable Long petForAdoptionId) {
        PetForAdoptionDTO updatedPetForAdoption = petForAdoptionService.updatePetForAdoption(petForAdoptionDTO, petForAdoptionId);
        return ResponseEntity.ok(updatedPetForAdoption);
    }


    @GetMapping
    public ResponseEntity<List<PetForAdoptionDTO>> getAllPetsForAdoption() {
        List<PetForAdoptionDTO> petsForAdoption = petForAdoptionService.getAllPetsForAdoption();
        return ResponseEntity.ok(petsForAdoption);
    }





    @GetMapping("/{id}")
    public ResponseEntity<PetForAdoptionDTO> getPetForAdoptionById(@PathVariable Long id) {
        PetForAdoptionDTO petForAdoption = petForAdoptionService.getPetForAdoptionById(id);
        return ResponseEntity.ok(petForAdoption);
    }




    @DeleteMapping("/{petForAdoptionId}")
    public ResponseEntity<Void> deletePetForAdoption(@PathVariable Long petForAdoptionId) {
        petForAdoptionService.deletePetForAdoption(petForAdoptionId);
        return ResponseEntity.noContent().build();
    }


}


