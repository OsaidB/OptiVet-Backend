package bzu.gradproj.optivet.backend.service;

import bzu.gradproj.optivet.backend.dto.AllergiesDTO;
import bzu.gradproj.optivet.backend.dto.ChronicConditionsDTO;

import java.util.List;

public interface ChronicConditionsService {

     ChronicConditionsDTO createChronicCondition(ChronicConditionsDTO chronicConditionDTO, Long petId);

     List<ChronicConditionsDTO> getChronicConditionsByPetId(Long petId);



     void deleteChronicCondition(Long chronicConditionId);
}
