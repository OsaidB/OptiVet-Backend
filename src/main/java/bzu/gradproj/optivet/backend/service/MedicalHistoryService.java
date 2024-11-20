package bzu.gradproj.optivet.backend.service;

import bzu.gradproj.optivet.backend.dto.MedicalHistoryDTO;

public interface MedicalHistoryService {

//  public void  deleteMedicalHistory(Long medicalHistoryId);
   MedicalHistoryDTO updateMedicalHistory(MedicalHistoryDTO medicalHistoryDTO, Long petId);


   MedicalHistoryDTO getMedicalHistory(Long petId);
}
