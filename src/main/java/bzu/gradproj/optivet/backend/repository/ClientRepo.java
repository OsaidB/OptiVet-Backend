package bzu.gradproj.optivet.backend.repository;

import bzu.gradproj.optivet.backend.model.entity.Client;
import bzu.gradproj.optivet.backend.model.entity.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {
    //    Optional<Client> findById(Long id);  // Returns Client specifically
//    List<Client> findAll();  // Only retrieves Client entities, not User in general
//    @EntityGraph(attributePaths = "pets")
//    @Query("SELECT c FROM Client c")
//    List<Client> findAllWithPets();

    @EntityGraph(attributePaths = "pets")
    @NotNull
    Optional<Client> findById(@NotNull Long id);

    Optional<Client> findByEmail(String email);

    @EntityGraph(attributePaths = "pets")
    @NotNull
    List<Client> findAll(); // This method will automatically load clients with pets

}
