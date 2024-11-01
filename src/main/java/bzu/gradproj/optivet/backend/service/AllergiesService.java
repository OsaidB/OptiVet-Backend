package bzu.gradproj.optivet.backend.service;

import bzu.gradproj.optivet.backend.dto.AllergiesDTO;
import bzu.gradproj.optivet.backend.model.entity.Allergies;
import bzu.gradproj.optivet.backend.model.entity.MedicalHistory;
import bzu.gradproj.optivet.backend.model.entity.Pet;

import java.util.List;
import java.util.stream.Collectors;

public interface AllergiesService {



    public AllergiesDTO createAllergy(AllergiesDTO allergyDTO, Long petId) ;





    public List<AllergiesDTO> getAllergiesByPetId(Long petId);


    public void deleteAllergy(Long allergyId);


}
