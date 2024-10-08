package bzu.gradproj.optivet.backend.service;

import bzu.gradproj.optivet.backend.dto.CommentDTO;
import java.util.List;

public interface CommentService {
    CommentDTO createComment(CommentDTO commentDTO);
    CommentDTO getCommentById(Long commentId);
    List<CommentDTO> getAllComments();
    List<CommentDTO> getCommentsByTaskId(Long taskId);
    CommentDTO updateComment(Long commentId,CommentDTO commentDTO);
    void deleteComment(Long commentId);
    void deleteCommentsByTaskId(Long taskId);

}
