package bzu.gradproj.optivet.backend.controller;

import bzu.gradproj.optivet.backend.dto.AppointmentDTO;
import bzu.gradproj.optivet.backend.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        AppointmentDTO createdAppointment = appointmentService.createAppointment(appointmentDTO);
        return ResponseEntity.ok(createdAppointment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable Long id, @RequestBody AppointmentDTO appointmentDTO) {
        AppointmentDTO updatedAppointment = appointmentService.updateAppointment(id, appointmentDTO);
        return ResponseEntity.ok(updatedAppointment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
        List<AppointmentDTO> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable Long id) {
        AppointmentDTO appointment = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(appointment);
    }

    @GetMapping("/available-slots")
    public ResponseEntity<List<AppointmentDTO>> getAvailableSlots(@RequestParam("vetId") Long vetId) {
        List<AppointmentDTO> availableSlots = appointmentService.findAvailableSlots(vetId);
        return ResponseEntity.ok(availableSlots);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByClientId(@PathVariable Long clientId) {
        List<AppointmentDTO> clientAppointments = appointmentService.findAppointmentsByClientId(clientId);
        return ResponseEntity.ok(clientAppointments);
    }

    @GetMapping("/appointments")
    public List<AppointmentDTO> getScheduledAppointments(@RequestParam Long vetId, @RequestParam String status) {
        return appointmentService.getAppointmentsByVetIdAndStatus(vetId, status);
    }

}
