package bzu.gradproj.optivet.backend.repository;

import bzu.gradproj.optivet.backend.model.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
    // Custom query method to find appointments by status
    List<Appointment> findByStatus(String status);
    List<Appointment> findByVetIdAndStatus(Long vetId, String status);
    List<Appointment> findByClientId(Long clientId);

}
