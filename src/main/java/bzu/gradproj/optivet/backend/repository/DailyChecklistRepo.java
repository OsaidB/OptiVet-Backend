package bzu.gradproj.optivet.backend.repository;

import bzu.gradproj.optivet.backend.model.entity.DailyChecklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface DailyChecklistRepo extends JpaRepository<DailyChecklist, Long> {

    List<DailyChecklist> findByPetId(Long petId);
    List<DailyChecklist> findByDate(LocalDate date);
    List<DailyChecklist> findByCriticalIssueFlagTrue();

    // New method to get checked pet IDs for a specific date
    @Query("SELECT dc.pet.id FROM DailyChecklist dc WHERE dc.date = :date")
    List<Long> findCheckedPetIdsByDate(@Param("date") LocalDate date);
}
