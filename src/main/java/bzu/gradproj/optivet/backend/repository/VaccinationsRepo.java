package bzu.gradproj.optivet.backend.repository;

import bzu.gradproj.optivet.backend.model.entity.Allergies;
import bzu.gradproj.optivet.backend.model.entity.MedicalSession;
import bzu.gradproj.optivet.backend.model.entity.Vaccinations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccinationsRepo extends JpaRepository<Vaccinations, Long> {
    List<Vaccinations> findByMedicalHistoryId(Long medicalHistoryId);
}