package bzu.gradproj.optivet.backend.service;

import bzu.gradproj.optivet.backend.dto.MedicalSessionDTO;
import java.util.List;

public interface MedicalSessionService {
    MedicalSessionDTO createSession(MedicalSessionDTO medicalSessionDTO, Long veterinarianId);
    MedicalSessionDTO updateSession(Long sessionId, MedicalSessionDTO medicalSessionDTO);
    void deleteSession(Long sessionId);
    MedicalSessionDTO getSessionById(Long sessionId);
    List<MedicalSessionDTO> getSessionsByPetId(Long petId);
    List<MedicalSessionDTO> getSessionsByOwnerId(Long ownerId);
}
