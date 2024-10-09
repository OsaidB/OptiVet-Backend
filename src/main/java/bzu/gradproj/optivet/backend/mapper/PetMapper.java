package bzu.gradproj.optivet.backend.mapper;

import bzu.gradproj.optivet.backend.dto.PetDTO;
import bzu.gradproj.optivet.backend.model.entity.Pet;

import java.util.List;
import java.util.stream.Collectors;

public class PetMapper {

    // Method to convert Pet to PetDTO
    public static PetDTO toPetDTO(Pet pet) {
        if (pet == null) {
            return null;
        }

        PetDTO petDTO = new PetDTO();
        petDTO.setName(pet.getName());
        petDTO.setType(pet.getType());
        petDTO.setBreed(pet.getBreed());
        petDTO.setBirthDate(pet.getBirthDate());
        petDTO.setMedicalHistory(pet.getMedicalHistory());
        return petDTO;
    }

    // Method to convert List<Pet> to List<PetDTO>
    public static List<PetDTO> petListToPetDTOList(List<Pet> pets) {
        return pets.stream()
                .map(PetMapper::toPetDTO)
                .collect(Collectors.toList());
    }

    // Method to convert PetDTO to Pet
    public static Pet toPetEntity(PetDTO petDTO) {
        if (petDTO == null) {
            return null;
        }

        Pet pet = new Pet();
        pet.setName(petDTO.getName());
        pet.setType(petDTO.getType());
        pet.setBreed(petDTO.getBreed());
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setMedicalHistory(petDTO.getMedicalHistory());
        return pet;
    }

    // Method to convert List<PetDTO> to List<Pet>
    public static List<Pet> petDTOListToPetList(List<PetDTO> petDTOs) {
        return petDTOs.stream()
                .map(PetMapper::toPetEntity)
                .collect(Collectors.toList());
    }
}
