package bzu.gradproj.optivet.backend.service.impl;

import bzu.gradproj.optivet.backend.dto.MedicalSessionDTO;
import bzu.gradproj.optivet.backend.mapper.MedicalSessionMapper;
import bzu.gradproj.optivet.backend.model.entity.MedicalSession;
import bzu.gradproj.optivet.backend.model.entity.User;
import bzu.gradproj.optivet.backend.repository.MedicalSessionRepo;
import bzu.gradproj.optivet.backend.repository.UserRepo;
import bzu.gradproj.optivet.backend.service.MedicalSessionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MedicalSessionServiceImpl implements MedicalSessionService {

    private final MedicalSessionRepo medicalSessionRepo;
    private final MedicalSessionMapper medicalSessionMapper;
    private final UserRepo userRepo;

    @Override
    public MedicalSessionDTO createSession(MedicalSessionDTO medicalSessionDTO, Long veterinarianId) {
        // Fetch the veterinarian (User entity) using the veterinarianId
        User veterinarian = userRepo.findById(veterinarianId)
                .orElseThrow(() -> new EntityNotFoundException("Veterinarian not found with id: " + veterinarianId));

        // Map the DTO to an entity
        MedicalSession medicalSession = medicalSessionMapper.toEntity(medicalSessionDTO);

        // Set the veterinarian for the session
        medicalSession.setVeterinarian(veterinarian);

        // Save the session
        MedicalSession savedSession = medicalSessionRepo.save(medicalSession);

        // Return the DTO for the saved session
        return medicalSessionMapper.toDTO(savedSession);
    }
    @Override
    public MedicalSessionDTO updateSession(Long sessionId, MedicalSessionDTO medicalSessionDTO) {
        MedicalSession existingSession = medicalSessionRepo.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Medical session not found"));
        medicalSessionMapper.toEntity(medicalSessionDTO); // Update entity values
        MedicalSession updatedSession = medicalSessionRepo.save(existingSession);
        return medicalSessionMapper.toDTO(updatedSession);
    }

    @Override
    public void deleteSession(Long sessionId) {
        medicalSessionRepo.deleteById(sessionId);
    }

    @Override
    public MedicalSessionDTO getSessionById(Long sessionId) {
        MedicalSession medicalSession = medicalSessionRepo.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Medical session not found"));
        return medicalSessionMapper.toDTO(medicalSession);
    }

    @Override
    public List<MedicalSessionDTO> getSessionsByPetId(Long petId) {
        List<MedicalSession> sessions = medicalSessionRepo.findByPetId(petId);
        return sessions.stream().map(medicalSessionMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<MedicalSessionDTO> getSessionsByOwnerId(Long ownerId) {
        List<MedicalSession> sessions = medicalSessionRepo.findByOwnerId(ownerId);
        return sessions.stream().map(medicalSessionMapper::toDTO).collect(Collectors.toList());
    }
}
