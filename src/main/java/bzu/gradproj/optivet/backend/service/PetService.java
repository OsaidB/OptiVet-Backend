package bzu.gradproj.optivet.backend.service;

import bzu.gradproj.optivet.backend.dto.PetDTO;
import java.util.List;

public interface PetService {
    PetDTO createPet(PetDTO petDTO);
    PetDTO getPetById(Long petId);
    List<PetDTO> getAllPets();
    List<PetDTO> getPetsByOwnerId(Long ownerId);
    PetDTO updatePet(Long petId, PetDTO petDTO);
    void deletePet(Long petId);
}
