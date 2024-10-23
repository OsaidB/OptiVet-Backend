package bzu.gradproj.optivet.backend.service;

import bzu.gradproj.optivet.backend.dto.AppointmentDTO;

import java.util.List;

public interface AppointmentService {
    AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);
    AppointmentDTO updateAppointment(Long id, AppointmentDTO appointmentDTO);
    void deleteAppointment(Long id);
    List<AppointmentDTO> getAllAppointments();
    AppointmentDTO getAppointmentById(Long id);
}
