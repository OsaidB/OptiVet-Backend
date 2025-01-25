package bzu.gradproj.optivet.backend.repository;

import bzu.gradproj.optivet.backend.dto.PetSummaryDTO;
import bzu.gradproj.optivet.backend.model.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    // Fetch PetSummaryDTO without medicalHistoryy
    @Query("SELECT new bzu.gradproj.optivet.backend.dto.PetSummaryDTO(" +
            "p.id, p.name, p.type, p.breed, p.birthDate, p.owner.id, " +
            "p.imageUrl, p.residencyType, p.deleted, p.manualId, p.gender) " +
            "FROM Pet p WHERE p.owner.id = :ownerId")
    List<PetSummaryDTO> findSummaryByOwnerId(@Param("ownerId") Long ownerId);

    // Original method
    List<Pet> findByOwnerId(Long ownerId);

    // New method to find pets by residency
    List<Pet> findByResidencyType(Pet.ResidencyType residencyType);
}
