package bzu.gradproj.optivet.backend.service.impl;

import bzu.gradproj.optivet.backend.dto.MedicalHistoryDTO;
import bzu.gradproj.optivet.backend.mapper.MedicalHistoryMapper;

import bzu.gradproj.optivet.backend.model.entity.*;
import bzu.gradproj.optivet.backend.repository.AllergiesRepo;
import bzu.gradproj.optivet.backend.repository.MedicalHistoryRepo;

import bzu.gradproj.optivet.backend.repository.PetRepository;
import bzu.gradproj.optivet.backend.service.MedicalHistoryService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MedicalHistoryServiceImpl implements MedicalHistoryService {

    private final MedicalHistoryRepo medicalHistoryRepo;
    private final MedicalHistoryMapper medicalHistoryMapper;
    private final PetRepository petRepo;
    private final AllergiesRepo allergiesRepo;


//
//   // @Override
//    public MedicalHistoryDTO createMedicalHistory(MedicalHistoryDTO medicalHistoryDTO, Long petId) {
//        // Fetch the veterinarian (User entity) using the veterinarianId
//        Pet existingPet = petRepo.findById(petId)
//                .orElseThrow(() -> new EntityNotFoundException("Pet not found with id: " + petId));
//
//        // Map the DTO to an entity
//        MedicalHistory medicalHistory = medicalHistoryMapper.toEntity(medicalHistoryDTO);
//
//        // Set the veterinarian for the medical history
//        //medicalHistory.setPet(existingPet);
//
//        // Save the medical history
//        MedicalHistory savedMedicalHistory = medicalHistoryRepo.save(medicalHistory);
//
//        // Return the DTO for the saved medical History
//        return medicalHistoryMapper.toDTO(savedMedicalHistory);
//    }


//   @Override
//    public MedicalHistoryDTO updateMedicalHistory(MedicalHistoryDTO medicalHistoryDTO,Long petId) {
//        Pet existingPet = petRepo.findById(petId)
//                .orElseThrow(() -> new EntityNotFoundException("Pet not found with id: " + petId));
//        MedicalHistory existingMedicalHistory = medicalHistoryRepo.findById(existingPet.getMedicalHistoryy().getId()).orElseThrow(() -> new EntityNotFoundException("Medical history not found"));
//        existingMedicalHistory.setNotes(medicalHistoryDTO.getNotes());
//        existingMedicalHistory.setDietaryPreferences(medicalHistoryDTO.getDietaryPreferences());
//
//
//        medicalHistoryRepo.save(existingMedicalHistory);
//        return medicalHistoryMapper.toDTO(existingMedicalHistory);
//
//
//
//    }


    @Override
    @Transactional
    public MedicalHistoryDTO updateMedicalHistory(MedicalHistoryDTO medicalHistoryDTO, Long petId) {
        Pet existingPet = petRepo.findById(petId)
                .orElseThrow(() -> new EntityNotFoundException("Pet not found with id: " + petId));
        MedicalHistory existingMedicalHistory = medicalHistoryRepo.findById(existingPet.getMedicalHistoryy().getId()).orElseThrow(() -> new EntityNotFoundException("Medical history not found"));
        existingMedicalHistory.setNotes(medicalHistoryDTO.getNotes());
        existingMedicalHistory.setDietaryPreferences(medicalHistoryDTO.getDietaryPreferences());
        existingMedicalHistory.setMedicalHistoryImageUrls(medicalHistoryDTO.getMedicalHistoryImageUrls());

        medicalHistoryRepo.save(existingMedicalHistory);
        return medicalHistoryMapper.toDTO(existingMedicalHistory);


    }


//    @Override
//    @Transactional
//    public void deleteMedicalHistory(Long medicalHistoryId) {
//        medicalHistoryRepo.deleteById(medicalHistoryId);
//    }



    @Override
    @Transactional
    public MedicalHistoryDTO getMedicalHistory(Long petId) {

        MedicalHistory existingMedicalHistory = petRepo.findById(petId).get().getMedicalHistoryy();
        return medicalHistoryMapper.toDTO(existingMedicalHistory);

    }


//    @Override
//    public List<MedicalSessionDTO> getSessionsByPetId(Long petId) {
//        List<MedicalSession> sessions = medicalSessionRepo.findByPetId(petId);
//        return sessions.stream().map(medicalSessionMapper::toDTO).collect(Collectors.toList());
//    }


}
