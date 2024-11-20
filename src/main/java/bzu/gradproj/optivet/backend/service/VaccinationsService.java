package bzu.gradproj.optivet.backend.service;

import bzu.gradproj.optivet.backend.dto.VaccinationsDTO;
import bzu.gradproj.optivet.backend.model.entity.MedicalHistory;
import bzu.gradproj.optivet.backend.model.entity.Pet;
import bzu.gradproj.optivet.backend.model.entity.Vaccinations;

import java.util.List;
import java.util.stream.Collectors;

public interface VaccinationsService {


     VaccinationsDTO createVaccination(VaccinationsDTO vaccinationsDTO, Long petId);

     List<VaccinationsDTO> getVaccinationsByPetId(Long petId);


     void deleteVaccination(Long VaccinationId);


}
