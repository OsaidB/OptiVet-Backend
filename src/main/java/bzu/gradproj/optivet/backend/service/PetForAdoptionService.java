package bzu.gradproj.optivet.backend.service;

import bzu.gradproj.optivet.backend.dto.CategoryDTO;
import bzu.gradproj.optivet.backend.dto.PetForAdoptionDTO;
import bzu.gradproj.optivet.backend.dto.ProductDTO;

import java.util.List;

public interface PetForAdoptionService {

    PetForAdoptionDTO createPetForAdoption(PetForAdoptionDTO petForAdoptionDTO);

    PetForAdoptionDTO updatePetForAdoption(PetForAdoptionDTO petForAdoptionDTO, Long petForAdoptionId);

    List<PetForAdoptionDTO> getAllPetsForAdoption();

    PetForAdoptionDTO getPetForAdoptionById(Long id);

    void deletePetForAdoption(Long petForAdoptionId);
}
