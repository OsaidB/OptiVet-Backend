package bzu.gradproj.optivet.backend.repository;

import bzu.gradproj.optivet.backend.model.entity.MedicalSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalSessionRepo extends JpaRepository<MedicalSession, Long> {
    List<MedicalSession> findByPetId(Long petId);
    List<MedicalSession> findByOwnerId(Long ownerId);
}
