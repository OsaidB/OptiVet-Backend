package bzu.gradproj.optivet.backend.service.impl;

import bzu.gradproj.optivet.backend.dto.*;
import bzu.gradproj.optivet.backend.mapper.AllergiesMapper;
import bzu.gradproj.optivet.backend.mapper.MedicalHistoryMapper;
import bzu.gradproj.optivet.backend.mapper.MedicalSessionMapper;
import bzu.gradproj.optivet.backend.mapper.VaccinationsMapper;
import bzu.gradproj.optivet.backend.model.entity.*;
import bzu.gradproj.optivet.backend.repository.*;
import bzu.gradproj.optivet.backend.service.AllergiesService;
import bzu.gradproj.optivet.backend.service.MedicalSessionService;
import bzu.gradproj.optivet.backend.service.VaccinationsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class VaccinationsServiceImpl implements VaccinationsService {

    private final VaccinationsMapper vaccinationsMapper;
    private final PetRepository petRepo;
    private final VaccinationsRepo vaccinationsRepo;

    private final MedicalHistoryRepo medRepo;


    @Override
    @Transactional
    public VaccinationsDTO createVaccination(VaccinationsDTO vaccinationDTO, Long petId) {



        vaccinationDTO.setMedicalHistoryId(petRepo.findById(petId).get().getMedicalHistoryy().getId());

        Vaccinations vaccination = vaccinationsMapper.toEntity(vaccinationDTO);

        MedicalHistory medicalHistory = medRepo.findById(vaccinationDTO.getMedicalHistoryId())
                .orElseThrow(() -> new RuntimeException("Medical history not found"));
        vaccination.setMedicalHistory(medicalHistory);

        Vaccinations savedVaccination = vaccinationsRepo.save(vaccination);
        return vaccinationsMapper.toDTO(savedVaccination);

    }


    @Override
    @Transactional(readOnly = true)
    public List<VaccinationsDTO> getVaccinationsByPetId(Long petId) {


        List<Vaccinations> vaccinations = vaccinationsRepo.findByMedicalHistoryId(petRepo.findById(petId).get().getMedicalHistoryy().getId());
        return vaccinations.stream().map(vaccinationsMapper::toDTO).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void deleteVaccination(Long vaccinationId) {
        vaccinationsRepo.deleteById(vaccinationId);
    }


}
