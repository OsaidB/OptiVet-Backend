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

        // Fetching the client, pet, and vet from the database to set relations
        Client client = clientRepository.findById(appointmentDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        Pet pet = petRepository.findById(appointmentDTO.getPetId())
                .orElseThrow(() -> new RuntimeException("Pet not found"));
        User vet = userRepository.findById(appointmentDTO.getVetId())
                .orElseThrow(() -> new RuntimeException("Vet not found"));

        appointment.setClient(client);
        appointment.setPet(pet);
        appointment.setVet(vet); // Assign the vet to the appointment

        appointmentRepository.save(appointment);
        return appointmentMapper.toDTO(appointment);
    }

    @Override
    @Transactional
    public AppointmentDTO updateAppointment(Long id, AppointmentDTO appointmentDTO) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        // Updating client, pet, and vet relationships if needed
        Client client = clientRepository.findById(appointmentDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        Pet pet = petRepository.findById(appointmentDTO.getPetId())
                .orElseThrow(() -> new RuntimeException("Pet not found"));
        User vet = userRepository.findById(appointmentDTO.getVetId())
                .orElseThrow(() -> new RuntimeException("Vet not found"));

        appointment.setClient(client);
        appointment.setPet(pet);
        appointment.setVet(vet);
        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        appointment.setStatus(appointmentDTO.getStatus());

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
}
