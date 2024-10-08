package bzu.gradproj.optivet.backend.service;

import bzu.gradproj.optivet.backend.dto.BoardDTO;

import java.util.List;

public interface BoardService {
    BoardDTO createBoard(Long projectId, Long roleId);
    BoardDTO getBoardById(Long boardId);
    List<BoardDTO> getAllBoards();
    List<BoardDTO> getBoardsByProjectId(Long projectId);
    BoardDTO updateBoard(Long boardId, BoardDTO updatedBoard);
    void deleteBoard(Long boardId);

    void addDefaultBoards(long projectId);
}
