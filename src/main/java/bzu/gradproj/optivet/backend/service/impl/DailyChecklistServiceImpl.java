package bzu.gradproj.optivet.backend.service.impl;

import bzu.gradproj.optivet.backend.dto.DailyChecklistDTO;
import bzu.gradproj.optivet.backend.dto.PetDTO;
import bzu.gradproj.optivet.backend.mapper.DailyChecklistMapper;
import bzu.gradproj.optivet.backend.mapper.PetMapper;
import bzu.gradproj.optivet.backend.model.entity.DailyChecklist;
import bzu.gradproj.optivet.backend.model.entity.Pet;
import bzu.gradproj.optivet.backend.repository.DailyChecklistRepo;
import bzu.gradproj.optivet.backend.repository.PetRepository;
import bzu.gradproj.optivet.backend.service.DailyChecklistService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DailyChecklistServiceImpl implements DailyChecklistService {

    private final DailyChecklistRepo dailyChecklistRepo;
    private final PetRepository petRepository;
    private final DailyChecklistMapper dailyChecklistMapper;

    @Autowired
    private PetMapper petMapper; // Injected PetMapper instance

    @Override
    public DailyChecklistDTO createDailyChecklist(DailyChecklistDTO dailyChecklistDTO) {
        DailyChecklist dailyChecklist = dailyChecklistMapper.toEntity(dailyChecklistDTO);
        DailyChecklist savedChecklist = dailyChecklistRepo.save(dailyChecklist);
        return dailyChecklistMapper.toDTO(savedChecklist);
    }

    @Override
    public DailyChecklistDTO updateDailyChecklist(Long id, DailyChecklistDTO dailyChecklistDTO) {
//        log.error("Attempting to update checklist with ID: {}", id);
//        log.error("Attempting to update checklist with DTO: {}", dailyChecklistDTO);

        // Fetch the existing checklist from the repository
        DailyChecklist existingChecklist = dailyChecklistRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Daily checklist not found with ID: " + id));

        // Manually update the fields
        existingChecklist.setDate(dailyChecklistDTO.getDate());
        existingChecklist.setEatingWell(dailyChecklistDTO.getEatingWell());
        existingChecklist.setDrinkingWater(dailyChecklistDTO.getDrinkingWater());
        existingChecklist.setActiveBehavior(dailyChecklistDTO.getActiveBehavior());
        existingChecklist.setNormalVitalSigns(dailyChecklistDTO.getNormalVitalSigns());
        existingChecklist.setHealthObservations(dailyChecklistDTO.getHealthObservations());
        existingChecklist.setWeightChange(dailyChecklistDTO.getWeightChange());
        existingChecklist.setInjuriesOrWounds(dailyChecklistDTO.getInjuriesOrWounds());
        existingChecklist.setFeedingCompleted(dailyChecklistDTO.getFeedingCompleted());
        existingChecklist.setCleanedLivingSpace(dailyChecklistDTO.getCleanedLivingSpace());
        existingChecklist.setPoopNormal(dailyChecklistDTO.getPoopNormal());
        existingChecklist.setPoopNotes(dailyChecklistDTO.getPoopNotes());
        existingChecklist.setCriticalIssueFlag(dailyChecklistDTO.getCriticalIssueFlag());
        existingChecklist.setCriticalNotes(dailyChecklistDTO.getCriticalNotes());

        // Update the relationship with the Pet entity
        Pet pet = petRepository.findById(dailyChecklistDTO.getPetId())
                .orElseThrow(() -> new EntityNotFoundException("Pet not found with ID: " + dailyChecklistDTO.getPetId()));
        existingChecklist.setPet(pet);

        // Save the updated checklist
        DailyChecklist updatedChecklist = dailyChecklistRepo.save(existingChecklist);

        // Return the updated DTO
        return dailyChecklistMapper.toDTO(updatedChecklist);
    }

    @Override
    public DailyChecklistDTO getDailyChecklistById(Long id) {
        DailyChecklist dailyChecklist = dailyChecklistRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Daily checklist not found with ID: " + id));
        return dailyChecklistMapper.toDTO(dailyChecklist);
    }

    @Override
    public List<DailyChecklistDTO> getDailyChecklistsByPetId(Long petId) {
        List<DailyChecklist> checklists = dailyChecklistRepo.findByPetId(petId);
        return checklists.stream()
                .map(dailyChecklistMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DailyChecklistDTO> getDailyChecklistsByDate(LocalDate date) {
        List<DailyChecklist> checklists = dailyChecklistRepo.findByDate(date);
        return checklists.stream()
                .map(dailyChecklistMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDailyChecklist(Long id) {
        if (!dailyChecklistRepo.existsById(id)) {
            throw new EntityNotFoundException("Daily checklist not found with ID: " + id);
        }
        dailyChecklistRepo.deleteById(id);
    }

    @Override
    public List<DailyChecklistDTO> getAllDailyChecklists() {
        return dailyChecklistRepo.findAll()
                .stream()
                .map(dailyChecklistMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DailyChecklistDTO> getDailyChecklistsWithCriticalIssues() {
        List<DailyChecklist> criticalChecklists = dailyChecklistRepo.findByCriticalIssueFlagTrue();
        return criticalChecklists.stream()
                .map(dailyChecklistMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> getCheckedPetIdsForToday(LocalDate date) {
        // Fetch pet IDs that have a daily checklist for the given date
        return dailyChecklistRepo.findCheckedPetIdsByDate(date);
    }

    @Override
    public List<Long> getUncheckedPetIdsForToday(LocalDate date) {
        List<Long> checkedIds = dailyChecklistRepo.findCheckedPetIdsByDate(date);
        return petRepository.findPetsNotInIds(checkedIds)
                .stream()
                .map(Pet::getId) // Extract IDs
                .collect(Collectors.toList());
    }

    @Override
    public List<PetDTO> getPetsByIds(List<Long> ids) {
        return petRepository.findAllById(ids)
                .stream()
                .map(petMapper::toDTO)
                .collect(Collectors.toList());
    }

}
