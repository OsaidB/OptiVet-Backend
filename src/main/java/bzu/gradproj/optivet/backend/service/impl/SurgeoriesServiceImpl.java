package bzu.gradproj.optivet.backend.service.impl;

import bzu.gradproj.optivet.backend.dto.*;
import bzu.gradproj.optivet.backend.exception.ResourceNotFoundException;
import bzu.gradproj.optivet.backend.mapper.AllergiesMapper;
import bzu.gradproj.optivet.backend.mapper.MedicalHistoryMapper;
import bzu.gradproj.optivet.backend.mapper.MedicalSessionMapper;
import bzu.gradproj.optivet.backend.mapper.SurgeoriesMapper;
import bzu.gradproj.optivet.backend.model.entity.*;
import bzu.gradproj.optivet.backend.repository.*;
import bzu.gradproj.optivet.backend.service.AllergiesService;
import bzu.gradproj.optivet.backend.service.MedicalSessionService;
import bzu.gradproj.optivet.backend.service.SurgeoriesService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class SurgeoriesServiceImpl implements SurgeoriesService {

    private final SurgeoriesMapper surgeoriesMapper;
    private final PetRepository petRepo;
    private final SurgeoriesRepo surgeoriesRepo;

    private final MedicalHistoryRepo medRepo;


    @Override
    public SurgeoriesDTO createSurgeory(SurgeoriesDTO surgeoryDTO, Long petId) {



        surgeoryDTO.setMedicalHistoryId(petRepo.findById(petId).get().getMedicalHistoryy().getId());

        Surgeories surgeory = surgeoriesMapper.toEntity(surgeoryDTO);

        MedicalHistory medicalHistory = medRepo.findById(surgeoryDTO.getMedicalHistoryId())
                .orElseThrow(() -> new RuntimeException("Medical history not found"));
        surgeory.setMedicalHistory(medicalHistory);

        Surgeories savedSurgeory = surgeoriesRepo.save(surgeory);
        return surgeoriesMapper.toDTO(savedSurgeory);

    }


    @Override
    @Transactional(readOnly = true)
    public List<SurgeoriesDTO> getSurgeoriesByPetId(Long petId) {


        List<Surgeories> surgeories = petRepo.findById(petId).get().getMedicalHistoryy().getSurgeories();
        //allergiesRepo.findByMedicalHistoryId(petRepo.findById(petId).get().getMedicalHistoryy().getId());
        return surgeories.stream().map(surgeoriesMapper::toDTO).collect(Collectors.toList());
    }


    @Override
    public void deleteSurgeory(Long allergyId) {
        if (!surgeoriesRepo.existsById(allergyId)) {
            throw new ResourceNotFoundException("Pet not found with id: " + allergyId);
        }
        surgeoriesRepo.deleteById(allergyId);
    }


}
