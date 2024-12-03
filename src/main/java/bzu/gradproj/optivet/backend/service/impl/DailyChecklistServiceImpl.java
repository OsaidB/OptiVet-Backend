package bzu.gradproj.optivet.backend.service.impl;

import bzu.gradproj.optivet.backend.dto.DailyChecklistDTO;
import bzu.gradproj.optivet.backend.mapper.DailyChecklistMapper;
import bzu.gradproj.optivet.backend.model.entity.DailyChecklist;
import bzu.gradproj.optivet.backend.repository.DailyChecklistRepo;
import bzu.gradproj.optivet.backend.service.DailyChecklistService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DailyChecklistServiceImpl implements DailyChecklistService {

    private final DailyChecklistRepo dailyChecklistRepo;
    private final DailyChecklistMapper dailyChecklistMapper;

    @Override
    public DailyChecklistDTO createDailyChecklist(DailyChecklistDTO dailyChecklistDTO) {
        DailyChecklist dailyChecklist = dailyChecklistMapper.toEntity(dailyChecklistDTO);
        DailyChecklist savedChecklist = dailyChecklistRepo.save(dailyChecklist);
        return dailyChecklistMapper.toDTO(savedChecklist);
    }

    @Override
    public DailyChecklistDTO updateDailyChecklist(Long id, DailyChecklistDTO dailyChecklistDTO) {
        DailyChecklist existingChecklist = dailyChecklistRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Daily checklist not found with ID: " + id));

        dailyChecklistMapper.updateFromDto(dailyChecklistDTO, existingChecklist);
        DailyChecklist updatedChecklist = dailyChecklistRepo.save(existingChecklist);
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
}
