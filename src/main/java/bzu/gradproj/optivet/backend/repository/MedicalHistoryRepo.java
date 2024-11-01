package bzu.gradproj.optivet.backend.repository;

import bzu.gradproj.optivet.backend.model.entity.MedicalHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalHistoryRepo extends JpaRepository<MedicalHistory, Long> {

}