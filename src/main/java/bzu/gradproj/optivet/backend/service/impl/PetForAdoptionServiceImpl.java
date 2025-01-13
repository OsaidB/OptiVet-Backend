package bzu.gradproj.optivet.backend.service.impl;

import bzu.gradproj.optivet.backend.dto.*;
import bzu.gradproj.optivet.backend.mapper.PetForAdoptionMapper;
import bzu.gradproj.optivet.backend.mapper.ProductMapper;
import bzu.gradproj.optivet.backend.model.entity.*;
import bzu.gradproj.optivet.backend.repository.*;
import bzu.gradproj.optivet.backend.service.PetForAdoptionService;
import bzu.gradproj.optivet.backend.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
public class PetForAdoptionServiceImpl implements PetForAdoptionService {

    private final PetForAdoptionMapper petForAdoptionMapper;
    private final PetForAdoptionRepo petForAdoptionRepo;

    @Transactional
    @Override
    public PetForAdoptionDTO createPetForAdoption(PetForAdoptionDTO petForAdoptionDTO) {

        PetForAdoption petForAdoption = petForAdoptionMapper.toEntity(petForAdoptionDTO);

        PetForAdoption savedPetForAdoption = petForAdoptionRepo.save(petForAdoption);

        return petForAdoptionMapper.toDTO(savedPetForAdoption);
    }


    @Override
    public PetForAdoptionDTO updatePetForAdoption(PetForAdoptionDTO petForAdoptionDTO, Long petForAdoptionId) {

        PetForAdoption existingPetForAdoption= petForAdoptionRepo.findById(petForAdoptionId).orElseThrow(() -> new EntityNotFoundException("Pet not found"));


        petForAdoptionRepo.save(existingPetForAdoption);
        return petForAdoptionMapper.toDTO(existingPetForAdoption);


    }


    @Override

    public List<PetForAdoptionDTO> getAllPetsForAdoption() {

        List<PetForAdoption> petsForAdoption = petForAdoptionRepo.findAll();
        return petForAdoptionMapper.toDTOList(petsForAdoption);
    }




    @Override
    public PetForAdoptionDTO getPetForAdoptionById(Long id) {

        PetForAdoption existingPetForAdoption = petForAdoptionRepo.findById(id).get();
        return petForAdoptionMapper.toDTO(existingPetForAdoption);

    }

    @Override

    public void deletePetForAdoption(Long petForAdoptionId) {
        petForAdoptionRepo.deleteById(petForAdoptionId);
    }


}

