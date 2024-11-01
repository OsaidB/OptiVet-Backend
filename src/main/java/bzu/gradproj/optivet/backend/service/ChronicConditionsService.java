package bzu.gradproj.optivet.backend.service;

import bzu.gradproj.optivet.backend.dto.AllergiesDTO;
import bzu.gradproj.optivet.backend.dto.ChronicConditionsDTO;

import java.util.List;

public interface ChronicConditionsService {

    public ChronicConditionsDTO createChronicCondition(ChronicConditionsDTO chronicConditionDTO, Long petId);

    public List<ChronicConditionsDTO> getChronicConditionsByPetId(Long petId);



    public void deleteChronicCondition(Long chronicConditionId);
}
