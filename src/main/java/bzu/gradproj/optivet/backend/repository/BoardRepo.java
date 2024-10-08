package bzu.gradproj.optivet.backend.repository;

import bzu.gradproj.optivet.backend.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepo extends JpaRepository<Board, Long> {
}
