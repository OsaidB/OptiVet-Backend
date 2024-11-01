package bzu.gradproj.optivet.backend.service;

import bzu.gradproj.optivet.backend.dto.MedicalHistoryDTO;

public interface MedicalHistoryService {

//  public void  deleteMedicalHistory(Long medicalHistoryId);
  public MedicalHistoryDTO updateMedicalHistory(MedicalHistoryDTO medicalHistoryDTO, Long petId);


  public MedicalHistoryDTO getMedicalHistory(Long petId);
}
