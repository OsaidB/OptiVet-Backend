package bzu.gradproj.optivet.backend.service;

import bzu.gradproj.optivet.backend.dto.AppointmentDTO;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface AppointmentService {
    AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);
    AppointmentDTO updateAppointment(Long id, AppointmentDTO appointmentDTO);
    void deleteAppointment(Long id);
    List<AppointmentDTO> getAllAppointments();
    AppointmentDTO getAppointmentById(Long id);

    @Transactional(readOnly = true)
    List<AppointmentDTO> findAvailableSlots(Long vetId);

    @Transactional(readOnly = true)
    List<AppointmentDTO> findAppointmentsByClientId(Long clientId);

    @Transactional(readOnly = true)
    List<AppointmentDTO> findScheduledAppointmentsByVetId(Long vetId);

    @Transactional(readOnly = true)
    List<AppointmentDTO> getAppointmentsByVetIdAndStatus(Long vetId, String status);

}
