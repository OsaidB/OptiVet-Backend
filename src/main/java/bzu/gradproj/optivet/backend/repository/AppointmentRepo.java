package bzu.gradproj.optivet.backend.repository;

import bzu.gradproj.optivet.backend.model.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
    // Custom query methods if needed
}
