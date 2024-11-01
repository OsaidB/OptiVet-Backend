package bzu.gradproj.optivet.backend.service.impl;

import bzu.gradproj.optivet.backend.dto.AllergiesDTO;
import bzu.gradproj.optivet.backend.dto.MedicalHistoryDTO;
import bzu.gradproj.optivet.backend.dto.MedicalSessionDTO;
import bzu.gradproj.optivet.backend.dto.PetDTO;
import bzu.gradproj.optivet.backend.exception.ResourceNotFoundException;
import bzu.gradproj.optivet.backend.mapper.AllergiesMapper;
import bzu.gradproj.optivet.backend.mapper.MedicalHistoryMapper;
import bzu.gradproj.optivet.backend.mapper.MedicalSessionMapper;
import bzu.gradproj.optivet.backend.model.entity.*;
import bzu.gradproj.optivet.backend.repository.*;
import bzu.gradproj.optivet.backend.service.AllergiesService;
import bzu.gradproj.optivet.backend.service.MedicalSessionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class AllergiesServiceImpl implements AllergiesService {

    private final AllergiesMapper allergiesMapper;
    private final PetRepository petRepo;
    private final AllergiesRepo allergiesRepo;

    private final MedicalHistoryRepo medRepo;


    @Override
    public AllergiesDTO createAllergy(AllergiesDTO allergyDTO, Long petId) {



        allergyDTO.setMedicalHistoryId(petRepo.findById(petId).get().getMedicalHistoryy().getId());

        Allergies allergy = allergiesMapper.toEntity(allergyDTO);

        MedicalHistory medicalHistory = medRepo.findById(allergyDTO.getMedicalHistoryId())
                .orElseThrow(() -> new RuntimeException("Medical history not found"));
        allergy.setMedicalHistory(medicalHistory);

        Allergies savedAllergy = allergiesRepo.save(allergy);
        return allergiesMapper.toDTO(savedAllergy);

    }


    @Override
    @Transactional(readOnly = true)
    public List<AllergiesDTO> getAllergiesByPetId(Long petId) {


        List<Allergies> allergies = petRepo.findById(petId).get().getMedicalHistoryy().getAllergies();
                //allergiesRepo.findByMedicalHistoryId(petRepo.findById(petId).get().getMedicalHistoryy().getId());
        return allergies.stream().map(allergiesMapper::toDTO).collect(Collectors.toList());
    }


    @Override
    public void deleteAllergy(Long allergyId) {
        if (!allergiesRepo.existsById(allergyId)) {
            throw new ResourceNotFoundException("Pet not found with id: " + allergyId);
        }
        allergiesRepo.deleteById(allergyId);
    }


}
