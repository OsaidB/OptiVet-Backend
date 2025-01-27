package bzu.gradproj.optivet.backend.repository;

import bzu.gradproj.optivet.backend.model.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByOwnerId(Long ownerId);

    // New method to find pets by residency
    List<Pet> findByResidencyType(Pet.ResidencyType residencyType);

    // New method to find pet IDs that are not in a specific list
    @Query("SELECT p FROM Pet p WHERE p.id NOT IN :ids")
    List<Pet> findPetsNotInIds(@Param("ids") List<Long> ids);
}
