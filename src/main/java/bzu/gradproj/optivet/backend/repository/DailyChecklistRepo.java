package bzu.gradproj.optivet.backend.repository;

import bzu.gradproj.optivet.backend.model.entity.DailyChecklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DailyChecklistRepo extends JpaRepository<DailyChecklist, Long> {

    List<DailyChecklist> findByPetId(Long petId);

    List<DailyChecklist> findByDate(LocalDate date);
}
