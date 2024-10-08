package bzu.gradproj.optivet.backend.mapper;

import bzu.gradproj.optivet.backend.dto.CommentDTO;
import bzu.gradproj.optivet.backend.model.entity.Comment;
import bzu.gradproj.optivet.backend.model.entity.Task;
import bzu.gradproj.optivet.backend.model.entity.User;

public class CommentMapper {
    public static CommentDTO mapToCommentDTO(Comment comment) {
        return new CommentDTO(
                comment.getCommentId(),
                comment.getTask().getTaskId(),
                comment.getCommentedBy().getId(),
                comment.getComment(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
    public static Comment mapToComment(CommentDTO commentDTO, User user, Task task) {
        return new Comment(
                commentDTO.getCommentId(),
                task,
                user,
                commentDTO.getComment(),
                commentDTO.getCreatedAt(),
                commentDTO.getUpdatedAt()
        );
    }
}
