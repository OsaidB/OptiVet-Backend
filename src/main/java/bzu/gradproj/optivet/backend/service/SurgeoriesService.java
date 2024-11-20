package bzu.gradproj.optivet.backend.service;

import bzu.gradproj.optivet.backend.dto.AllergiesDTO;
import bzu.gradproj.optivet.backend.dto.SurgeoriesDTO;
import bzu.gradproj.optivet.backend.model.entity.Allergies;
import bzu.gradproj.optivet.backend.model.entity.MedicalHistory;
import bzu.gradproj.optivet.backend.model.entity.Pet;

import java.util.List;
import java.util.stream.Collectors;

public interface SurgeoriesService {



     SurgeoriesDTO createSurgeory(SurgeoriesDTO surgeoryDTO, Long petId) ;





     List<SurgeoriesDTO> getSurgeoriesByPetId(Long petId);


     void deleteSurgeory(Long surgeoryId);


}
