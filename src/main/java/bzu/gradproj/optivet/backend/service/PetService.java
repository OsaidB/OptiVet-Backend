package bzu.gradproj.optivet.backend.service;

import bzu.gradproj.optivet.backend.dto.PetDTO;
import bzu.gradproj.optivet.backend.model.entity.Pet;

import java.util.List;

public interface PetService {
    PetDTO createPet(PetDTO petDTO);
    PetDTO getPetById(Long petId);
    List<PetDTO> getAllPets();
    List<PetDTO> getPetsByOwnerId(Long ownerId);
    List<PetDTO> getPetsByResidencyType(Pet.ResidencyType residencyType);
    PetDTO updatePet(Long petId, PetDTO petDTO);
    Long  getTotalPetsCount();
    void deletePet(Long petId);
}
