package bzu.gradproj.optivet.backend.repository;

import bzu.gradproj.optivet.backend.model.entity.FunctionalRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncRoleRepo extends JpaRepository<FunctionalRole, Long> {
    FunctionalRole findByRoleName(String roleName);
}
