package bzu.gradproj.optivet.backend.service.impl;

import bzu.gradproj.optivet.backend.dto.PetDTO;
import bzu.gradproj.optivet.backend.exception.ResourceNotFoundException;
import bzu.gradproj.optivet.backend.mapper.PetMapper;
import bzu.gradproj.optivet.backend.model.entity.Pet;
import bzu.gradproj.optivet.backend.repository.PetRepository;
import bzu.gradproj.optivet.backend.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Override
    public PetDTO createPet(PetDTO petDTO) {
        Pet pet = PetMapper.INSTANCE.toPetEntity(petDTO);
        Pet savedPet = petRepository.save(pet);
        return PetMapper.INSTANCE.toPetDTO(savedPet);
    }

    @Override
    public PetDTO getPetById(Long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + petId));
        return PetMapper.INSTANCE.toPetDTO(pet);
    }

    @Override
    public List<PetDTO> getAllPets() {
        return petRepository.findAll().stream()
                .map(PetMapper.INSTANCE::toPetDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PetDTO> getPetsByOwnerId(Long ownerId) {
        return petRepository.findByOwnerId(ownerId).stream()
                .map(PetMapper.INSTANCE::toPetDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PetDTO updatePet(Long petId, PetDTO petDTO) {
        Pet existingPet = petRepository.findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + petId));

        existingPet.setName(petDTO.getName());
        existingPet.setType(petDTO.getType());
        existingPet.setBreed(petDTO.getBreed());
        existingPet.setBirthDate(petDTO.getBirthDate());
        existingPet.setMedicalHistory(petDTO.getMedicalHistory());

        Pet updatedPet = petRepository.save(existingPet);
        return PetMapper.INSTANCE.toPetDTO(updatedPet);
    }

    @Override
    public void deletePet(Long petId) {
        if (!petRepository.existsById(petId)) {
            throw new ResourceNotFoundException("Pet not found with id: " + petId);
        }
        petRepository.deleteById(petId);
    }
}
