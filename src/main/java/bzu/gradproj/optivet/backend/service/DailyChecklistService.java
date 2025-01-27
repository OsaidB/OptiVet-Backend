package bzu.gradproj.optivet.backend.service;

import bzu.gradproj.optivet.backend.dto.DailyChecklistDTO;
import bzu.gradproj.optivet.backend.dto.PetDTO;

import java.time.LocalDate;
import java.util.List;

public interface DailyChecklistService {

    DailyChecklistDTO createDailyChecklist(DailyChecklistDTO dailyChecklistDTO);

    DailyChecklistDTO updateDailyChecklist(Long id, DailyChecklistDTO dailyChecklistDTO);

    DailyChecklistDTO getDailyChecklistById(Long id);

    List<DailyChecklistDTO> getDailyChecklistsByPetId(Long petId);

    List<DailyChecklistDTO> getDailyChecklistsByDate(LocalDate date);

    List<DailyChecklistDTO> getAllDailyChecklists(); // Add this method to the interface

    void deleteDailyChecklist(Long id);

    List<DailyChecklistDTO> getDailyChecklistsWithCriticalIssues();

    List<Long> getCheckedPetIdsForToday(LocalDate date);

    List<Long> getUncheckedPetIdsForToday(LocalDate date);

    List<PetDTO> getPetsByIds(List<Long> ids);
}
