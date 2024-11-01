package bzu.gradproj.optivet.backend.service.impl;

import bzu.gradproj.optivet.backend.dto.*;
import bzu.gradproj.optivet.backend.mapper.AllergiesMapper;
import bzu.gradproj.optivet.backend.mapper.ChronicConditionsMapper;
import bzu.gradproj.optivet.backend.mapper.MedicalHistoryMapper;
import bzu.gradproj.optivet.backend.mapper.MedicalSessionMapper;
import bzu.gradproj.optivet.backend.model.entity.*;
import bzu.gradproj.optivet.backend.repository.*;
import bzu.gradproj.optivet.backend.service.AllergiesService;
import bzu.gradproj.optivet.backend.service.ChronicConditionsService;
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
public class ChronicConditionsServiceImpl implements ChronicConditionsService {

    private final ChronicConditionsMapper chronicConditionsMapper;
    private final PetRepository petRepo;
    private final ChronicConditionsRepo chronicConditionsRepo;

    private final MedicalHistoryRepo medRepo;



    @Override
    public ChronicConditionsDTO createChronicCondition(ChronicConditionsDTO chronicConditionDTO, Long petId) {



        chronicConditionDTO.setMedicalHistoryId(petRepo.findById(petId).get().getMedicalHistoryy().getId());

        ChronicConditions chronicCondition = chronicConditionsMapper.toEntity(chronicConditionDTO);

        MedicalHistory medicalHistory = medRepo.findById(chronicConditionDTO.getMedicalHistoryId())
                .orElseThrow(() -> new RuntimeException("Medical history not found"));
        chronicCondition.setMedicalHistory(medicalHistory);

        ChronicConditions savedChronicCondition = chronicConditionsRepo.save(chronicCondition);
        return chronicConditionsMapper.toDTO(savedChronicCondition);

    }


    @Override
    @Transactional(readOnly = true)
    public List<ChronicConditionsDTO> getChronicConditionsByPetId(Long petId) {


        List<ChronicConditions> chronicConditions = chronicConditionsRepo.findByMedicalHistoryId(petRepo.findById(petId).get().getMedicalHistoryy().getId());
        return chronicConditions.stream().map(chronicConditionsMapper::toDTO).collect(Collectors.toList());
    }


    @Override
    public void deleteChronicCondition(Long chronicConditionId) {
        chronicConditionsRepo.deleteById(chronicConditionId);
    }


}
