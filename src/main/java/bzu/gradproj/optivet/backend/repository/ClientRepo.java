package bzu.gradproj.optivet.backend.repository;

import bzu.gradproj.optivet.backend.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
    Optional<Client> findById(Long id);  // Returns Client specifically
    List<Client> findAll();  // Only retrieves Client entities, not User in general
}
