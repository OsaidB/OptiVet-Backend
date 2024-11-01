package bzu.gradproj.optivet.backend.repository;

import bzu.gradproj.optivet.backend.model.entity.Allergies;
import bzu.gradproj.optivet.backend.model.entity.ChronicConditions;
import bzu.gradproj.optivet.backend.model.entity.MedicalSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChronicConditionsRepo extends JpaRepository<ChronicConditions, Long> {
    List<ChronicConditions> findByMedicalHistoryId(Long medicalHistoryId);
}