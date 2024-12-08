package bzu.gradproj.optivet.backend.service.impl;

import bzu.gradproj.optivet.backend.dto.AppointmentDTO;
import bzu.gradproj.optivet.backend.mapper.AppointmentMapper;
import bzu.gradproj.optivet.backend.model.entity.Appointment;
import bzu.gradproj.optivet.backend.model.entity.Client;
import bzu.gradproj.optivet.backend.model.entity.Pet;
import bzu.gradproj.optivet.backend.model.entity.User;
import bzu.gradproj.optivet.backend.repository.AppointmentRepo;
import bzu.gradproj.optivet.backend.repository.ClientRepo;
import bzu.gradproj.optivet.backend.repository.PetRepository;
import bzu.gradproj.optivet.backend.repository.UserRepo;
import bzu.gradproj.optivet.backend.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepo appointmentRepository;
    private final ClientRepo clientRepository;
    private final PetRepository petRepository;
    private final UserRepo userRepository;
    private final AppointmentMapper appointmentMapper;

    @Override
    @Transactional
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = appointmentMapper.toEntity(appointmentDTO);

        // Fetching the client from the database to set relations
        Client client = null;
        if (appointmentDTO.getClientId() != null) {
            client = clientRepository.findById(appointmentDTO.getClientId())
                    .orElseThrow(() -> new RuntimeException("Client not found"));
            appointment.setClient(client); // Set the pet only if it exists
        } else {
            appointment.setClient(null); // Set pet to null if not provided
        }

        // Check if petId is null
        if (appointmentDTO.getPetId() != null) {
            Pet pet = petRepository.findById(appointmentDTO.getPetId())
                    .orElseThrow(() -> new RuntimeException("Pet not found"));
            appointment.setPet(pet); // Set the pet only if it exists
        } else {
            appointment.setPet(null); // Set pet to null if not provided
        }

        User vet = userRepository.findById(appointmentDTO.getVetId())
                .orElseThrow(() -> new RuntimeException("Vet not found"));

        appointment.setClient(client);
        appointment.setVet(vet); // Assign the vet to the appointment

        appointment.setDuration(appointmentDTO.getDuration()); // Set the duration
        appointmentRepository.save(appointment);
        return appointmentMapper.toDTO(appointment);
    }


//    @Override
//    @Transactional
//    public AppointmentDTO updateAppointment(Long id, AppointmentDTO appointmentDTO) {
//        Appointment appointment = appointmentRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Appointment not found"));
//
//        // Updating client, pet, and vet relationships if needed
//        Client client = clientRepository.findById(appointmentDTO.getClientId())
//                .orElseThrow(() -> new RuntimeException("Client not found"));
//        Pet pet = petRepository.findById(appointmentDTO.getPetId())
//                .orElseThrow(() -> new RuntimeException("Pet not found"));
//        User vet = userRepository.findById(appointmentDTO.getVetId())
//                .orElseThrow(() -> new RuntimeException("Vet not found"));
//
//        appointment.setClient(client);
//        appointment.setPet(pet);
//        appointment.setVet(vet);
//        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
//        appointment.setStatus(appointmentDTO.getStatus());
//
//        appointmentRepository.save(appointment);
//        return appointmentMapper.toDTO(appointment);
//    }

    @Override
    @Transactional
    public AppointmentDTO updateAppointment(Long id, AppointmentDTO appointmentDTO) {
        // Find the existing appointment by ID or throw an exception if not found
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        // Update the client if clientId is provided; otherwise, set client to null
        if (appointmentDTO.getClientId() != null) {
            Client client = clientRepository.findById(appointmentDTO.getClientId())
                    .orElseThrow(() -> new RuntimeException("Client not found"));
            appointment.setClient(client);
        } else {
            // Clear the client if clientId is null
            appointment.setClient(null);
        }

        // Update the pet if petId is provided; otherwise, set pet to null
        if (appointmentDTO.getPetId() != null) {
            Pet pet = petRepository.findById(appointmentDTO.getPetId())
                    .orElseThrow(() -> new RuntimeException("Pet not found"));
            appointment.setPet(pet);
        } else {
            // Clear the pet if petId is null
            appointment.setPet(null);
        }

        // Update the vet (vetId should always be provided)
        User vet = userRepository.findById(appointmentDTO.getVetId())
                .orElseThrow(() -> new RuntimeException("Vet not found"));
        appointment.setVet(vet);

        // Update appointment date and status
        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        appointment.setStatus(appointmentDTO.getStatus());
        appointment.setDuration(appointmentDTO.getDuration()); // Update the duration


        // Save the updated appointment and return the DTO
        appointmentRepository.save(appointment);
        return appointmentMapper.toDTO(appointment);
    }



    @Override
    @Transactional
    public void deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointmentRepository.delete(appointment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentDTO> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AppointmentDTO getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        return appointmentMapper.toDTO(appointment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentDTO> findAvailableSlots(Long vetId) {
        // Fetch appointments with the status "AVAILABLE" and the given vetId
        List<Appointment> availableAppointments = appointmentRepository.findByVetIdAndStatus(vetId, "AVAILABLE");

        // Convert to DTOs and return the result
        return availableAppointments.stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentDTO> findAppointmentsByClientId(Long clientId) {
        // Fetch appointments for the given clientId
        List<Appointment> clientAppointments = appointmentRepository.findByClientId(clientId);

        // Convert to DTOs and return the result
        return clientAppointments.stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentDTO> findScheduledAppointmentsByVetId(Long vetId) {
        List<Appointment> scheduledAppointments = appointmentRepository.findByVetIdAndStatus(vetId, "SCHEDULED");
        return scheduledAppointments.stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentDTO> getAppointmentsByVetIdAndStatus(Long vetId, String status) {
        List<Appointment> appointments = appointmentRepository.findByVetIdAndStatus(vetId, status);
        return appointments.stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentDTO> findScheduledAppointmentsByClientId(Long clientId) {
        // Fetch appointments with the status "SCHEDULED" for the given clientId
        List<Appointment> scheduledAppointments = appointmentRepository.findByClientIdAndStatus(clientId, "SCHEDULED");

        // Convert to DTOs and return the result
        return scheduledAppointments.stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    @Transactional(readOnly = true)
    public List<AppointmentDTO> getAppointmentsByVetId(Long vetId) {
        // Fetch appointments for the given vetId
        List<Appointment> appointments = appointmentRepository.findByVetId(vetId);

        // Convert to DTOs and return the result
        return appointments.stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());
    }

}
