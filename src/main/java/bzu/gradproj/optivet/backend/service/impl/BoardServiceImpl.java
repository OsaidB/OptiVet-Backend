package bzu.gradproj.optivet.backend.service.impl;

import bzu.gradproj.optivet.backend.dto.BoardDTO;
import bzu.gradproj.optivet.backend.exception.ResourceNotFoundException;
import bzu.gradproj.optivet.backend.mapper.BoardMapper;
import bzu.gradproj.optivet.backend.model.entity.Board;
//import bzu.gradproj.optivet.backend.model.entity.FunctionalRole;
import bzu.gradproj.optivet.backend.repository.BoardRepo;
//import bzu.gradproj.optivet.backend.repository.FuncRoleRepo;
import bzu.gradproj.optivet.backend.service.BoardService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import bzu.gradproj.optivet.backend.repository.TaskRepo; // Import TaskRepo

@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepo boardRepo;



    @Autowired
    private TaskRepo taskRepo; // Add TaskRepo dependency



//    @Override
//    public BoardDTO createBoard(Long projectId, Long roleId) {
//
//        FunctionalRole role = funcRoleRepo.findById(roleId)
//                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
//
//        Board board = new Board();
//        board.setName(role.getRoleName());
//        board.setRole(role);
//
//        Project project = projectRepo.findById(projectId)
//                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + projectId));
//
//        board.setProject(project);
//        board.setCreatedAt(LocalDateTime.now());
//        board.setUpdatedAt(LocalDateTime.now());
//
//        Board savedBoard = boardRepo.save(board);
//        return BoardMapper.INSTANCE.toBoardDTO(savedBoard);
//    }

    @Override
    public BoardDTO getBoardById(Long boardId) {
        Board board = boardRepo.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found with id: " + boardId));
        return BoardMapper.INSTANCE.toBoardDTO(board);
    }

    @Override
    public List<BoardDTO> getAllBoards() {
        List<Board> boards = boardRepo.findAll();
        return boards.stream().map(BoardMapper.INSTANCE::toBoardDTO).collect(Collectors.toList());
    }


    @Override
    public BoardDTO updateBoard(Long boardId, BoardDTO updatedBoard) {
        Board board = boardRepo.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found with id: " + boardId));

        board.setName(updatedBoard.getName());
//        board.setCreatedAt(updatedBoard.getCreatedAt());
        board.setUpdatedAt(updatedBoard.getUpdatedAt());

        Board saveBoard = boardRepo.save(board);
        return BoardMapper.INSTANCE.toBoardDTO(saveBoard);
    }

    @Override
    @Transactional // Ensure that the delete operation is transactional
    public void deleteBoard(Long boardId) {
        Board board = boardRepo.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found with id: " + boardId));

        // Remove all tasks associated with the board
        taskRepo.deleteByBoardBoardId(boardId);

        // Delete the board
        boardRepo.delete(board);
    }

}
