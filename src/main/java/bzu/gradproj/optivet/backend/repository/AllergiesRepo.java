package bzu.gradproj.optivet.backend.repository;

import bzu.gradproj.optivet.backend.model.entity.Allergies;
import bzu.gradproj.optivet.backend.model.entity.MedicalSession;
import bzu.gradproj.optivet.backend.model.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllergiesRepo extends JpaRepository<Allergies, Long> {
    List<Allergies> findByMedicalHistoryId(Long medicalHistoryId);
}