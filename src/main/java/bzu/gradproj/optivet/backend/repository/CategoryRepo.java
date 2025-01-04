package bzu.gradproj.optivet.backend.repository;

import bzu.gradproj.optivet.backend.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

}