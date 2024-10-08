package bzu.gradproj.optivet.backend.service.impl;

import bzu.gradproj.optivet.backend.dto.CommentDTO;
import bzu.gradproj.optivet.backend.exception.ResourceNotFoundException;
import bzu.gradproj.optivet.backend.mapper.CommentMapper;
import bzu.gradproj.optivet.backend.model.entity.Comment;
import bzu.gradproj.optivet.backend.model.entity.Task;
import bzu.gradproj.optivet.backend.model.entity.User;
import bzu.gradproj.optivet.backend.repository.CommentRepo;
import bzu.gradproj.optivet.backend.repository.TaskRepo;
import bzu.gradproj.optivet.backend.repository.UserRepo;
import bzu.gradproj.optivet.backend.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TaskRepo taskRepo;

    @Override
    public CommentDTO createComment(CommentDTO commentDTO) {
        User user = userRepo.findById(commentDTO.getCommentedBy())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found: " + commentDTO.getCommentedBy()));
        Task task = taskRepo.findById(commentDTO.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task Not Found: " + commentDTO.getTaskId()));

        Comment comment = CommentMapper.mapToComment(commentDTO, user, task);
        Comment savedComment = commentRepo.save(comment);
        return CommentMapper.mapToCommentDTO(savedComment);
    }

    @Override
    public CommentDTO getCommentById(Long commentId) {
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment Not Found: " + commentId));
        return CommentMapper.mapToCommentDTO(comment);
    }

    @Override
    public List<CommentDTO> getAllComments() {
        List<Comment> comments = commentRepo.findAll();
        return comments.stream().map(CommentMapper::mapToCommentDTO).collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> getCommentsByTaskId(Long taskId) {
        taskRepo.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task Not Found: " + taskId));
        List<Comment> comments = commentRepo.findByTaskTaskId(taskId);
        return comments.stream().map(CommentMapper::mapToCommentDTO).collect(Collectors.toList());
    }

    @Override
    public CommentDTO updateComment(Long commentId, CommentDTO updatedCommentDTO) {
        User user = userRepo.findById(updatedCommentDTO.getCommentedBy())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found: " + updatedCommentDTO.getCommentedBy()));
        Task task = taskRepo.findById(updatedCommentDTO.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task Not Found: " + updatedCommentDTO.getTaskId()));
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment Not Found: " + updatedCommentDTO.getCommentId()));

        comment.setCommentedBy(user);
        comment.setUpdatedAt(updatedCommentDTO.getUpdatedAt());
        comment.setComment(updatedCommentDTO.getComment());
        comment.setTask(task);

        Comment savedComment = commentRepo.save(comment);
        return CommentMapper.mapToCommentDTO(savedComment);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment Not Found: " + commentId));
        commentRepo.delete(comment);
    }

    // New method to delete all comments associated with a specific task
    @Override
    public void deleteCommentsByTaskId(Long taskId) {
        // Check if task exists
        taskRepo.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task Not Found with ID: " + taskId));

        // Delete all comments related to the task
        List<Comment> comments = commentRepo.findByTaskTaskId(taskId);
        commentRepo.deleteAll(comments);
    }
}
