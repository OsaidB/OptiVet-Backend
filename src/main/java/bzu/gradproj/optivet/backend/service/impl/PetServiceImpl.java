package bzu.gradproj.optivet.backend.service.impl;

import bzu.gradproj.optivet.backend.dto.PetDTO;
import bzu.gradproj.optivet.backend.exception.ResourceNotFoundException;
import bzu.gradproj.optivet.backend.mapper.ClientMapper;
import bzu.gradproj.optivet.backend.mapper.PetMapper;
import bzu.gradproj.optivet.backend.model.entity.MedicalHistory;
import bzu.gradproj.optivet.backend.model.entity.Pet;
import bzu.gradproj.optivet.backend.repository.MedicalHistoryRepo;
import bzu.gradproj.optivet.backend.repository.PetRepository;
import bzu.gradproj.optivet.backend.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


//repoPet
@Service

public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    //    @Autowired
//    private MedicalHistoryRepo medicalHistoryRepository;
    @Autowired
    private ClientMapper clientMapper; // Injected ClientMapper instance

    @Autowired
    private MedicalHistoryRepo medicalHistoryRepo; // Injected ClientMapper instance

    @Autowired
    private PetMapper petMapper; // Injected PetMapper instance

    @Override
    @Transactional
    public PetDTO createPet(PetDTO petDTO) {
        Pet pet = petMapper.toEntity(petDTO); // Use the injected mapper
       // pet.setMedicalHistoryy(medicalHistoryRepo.save(new MedicalHistory()));
        MedicalHistory medicalHistory=new MedicalHistory();
//        medicalHistory.setPet(pet);
        pet.setMedicalHistoryy(medicalHistory);

        Pet savedPet = petRepository.save(pet);
//medicalHistoryRepo.save(medicalHistory);

        return petMapper.toDTO(savedPet);
    }

    @Override
    @Transactional
    public PetDTO getPetById(Long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + petId));
        return petMapper.toDTO(pet);
    }

    @Override
    @Transactional
    public List<PetDTO> getAllPets() {
        return petRepository.findAll().stream()
                .map(petMapper::toDTO)  // Use the injected mapper
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<PetDTO> getPetsByOwnerId(Long ownerId) {
        return petRepository.findByOwnerId(ownerId).stream()
                .map(petMapper::toDTO)  // Use the injected mapper
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<PetDTO> getPetsByResidencyType(Pet.ResidencyType residencyType) {
        return petRepository.findByResidencyType(residencyType).stream()
                .map(petMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public PetDTO updatePet(Long petId, PetDTO petDTO) {
        Pet existingPet = petRepository.findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + petId));

        existingPet.setName(petDTO.getName());
        existingPet.setType(petDTO.getType());
        existingPet.setBreed(petDTO.getBreed());
        existingPet.setBirthDate(petDTO.getBirthDate());
        existingPet.setMedicalHistory(petDTO.getMedicalHistory());
        existingPet.setGender(petDTO.getGender());
        existingPet.setManualId(petDTO.getManualId());
        existingPet.setDeleted(petDTO.isDeleted());

        Pet updatedPet = petRepository.save(existingPet);
        return petMapper.toDTO(updatedPet);
    }

    @Override
    public Long getTotalPetsCount() {
        return petRepository.count();
    }

    @Override
    @Transactional
    public void deletePet(Long petId) {
        if (!petRepository.existsById(petId)) {
            throw new ResourceNotFoundException("Pet not found with id: " + petId);
        }
        petRepository.deleteById(petId);
    }

    @Override
    @Transactional
    public void softDeletePet(Long petId) {
        Pet existingPet = petRepository.findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + petId));

        existingPet.setDeleted(true);
        petRepository.save(existingPet);
    }
}
