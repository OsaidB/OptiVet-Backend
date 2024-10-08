package bzu.gradproj.optivet.backend.service.impl;

import bzu.gradproj.optivet.backend.dto.TaskDTO;
import bzu.gradproj.optivet.backend.exception.ResourceNotFoundException;
import bzu.gradproj.optivet.backend.mapper.TaskMapper;
import bzu.gradproj.optivet.backend.model.entity.Board;
import bzu.gradproj.optivet.backend.model.entity.Project;
import bzu.gradproj.optivet.backend.model.entity.Task;
import bzu.gradproj.optivet.backend.model.entity.User;
import bzu.gradproj.optivet.backend.repository.BoardRepo;
import bzu.gradproj.optivet.backend.repository.ProjectRepo;
import bzu.gradproj.optivet.backend.repository.TaskRepo;
import bzu.gradproj.optivet.backend.repository.UserRepo;
import bzu.gradproj.optivet.backend.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import bzu.gradproj.optivet.backend.service.CommentService; // Import the CommentService

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    @Autowired
    private final TaskRepo taskRepo;

    @Autowired
    private final ProjectRepo projectRepo;

    @Autowired
    private final UserRepo userRepo;

    @Autowired
    private final BoardRepo boardRepo;

    @Autowired
    private final CommentService commentService; // Add this

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        Project project = projectRepo.findById(taskDTO.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + taskDTO.getProjectId()));

        Board board = boardRepo.findById(taskDTO.getBoardId())
                .orElseThrow(() -> new ResourceNotFoundException("Board not found with id: " + taskDTO.getBoardId()));

//        List<User> projectMembers = userRepo.findByProjectId(taskDTO.getProjectId());

        User assignedTo = null;
//        if (taskDTO.getAssignedToUserId() != null) {
//            assignedTo = projectMembers.stream()
//                    .filter(user -> user.getId().equals(taskDTO.getAssignedToUserId()))
//                    .findFirst()
//                    .orElseThrow(() -> new ResourceNotFoundException("Assigned user is not a member of the project"));
//        }

        Task task = TaskMapper.INSTANCE.toTaskEntity(taskDTO);
        task.setAssignedTo(assignedTo);
        task.setBoard(board);
        task.setProject(project);

        // Save the task with the due date
        Task savedTask = taskRepo.save(task);

        return TaskMapper.INSTANCE.toTaskDTO(savedTask);
    }

    @Override
    public TaskDTO getTaskById(Long taskId) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));
        return TaskMapper.INSTANCE.toTaskDTO(task);
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskRepo.findAll();
        return tasks.stream()
                .map(TaskMapper.INSTANCE::toTaskDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO updateTask(Long taskId, TaskDTO updatedTaskDTO) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));

        if (!task.getProject().getProjectId().equals(updatedTaskDTO.getProjectId())) {
            throw new IllegalArgumentException("Changing the projectId is not allowed.");
        }

        Board board = boardRepo.findById(updatedTaskDTO.getBoardId())
                .orElseThrow(() -> new ResourceNotFoundException("Board not found with id: " + updatedTaskDTO.getBoardId()));
        task.setBoard(board);

        task.setTaskName(updatedTaskDTO.getTaskName());

//        task.setAssignedTo(updatedTaskDTO.getAssignedToUserId());

        task.setTaskDescription(updatedTaskDTO.getTaskDescription());
        task.setStatus(updatedTaskDTO.getStatus());
        task.setPriority(updatedTaskDTO.getPriority());
        task.setUpdatedAt(updatedTaskDTO.getUpdatedAt());

//        List<User> projectMembers = userRepo.findByProjectId(updatedTaskDTO.getProjectId());
        if (updatedTaskDTO.getAssignedToUserId() != null) {
//            User assignedTo = projectMembers.stream()
//                    .filter(user -> user.getId().equals(updatedTaskDTO.getAssignedToUserId()))
//                    .findFirst()
//                    .orElseThrow(() -> new ResourceNotFoundException("Assigned user is not a member of the project"));
//            task.setAssignedTo(assignedTo);
        }else {
            task.setAssignedTo(null);
        }

        // Update the due date
        task.setDueDate(updatedTaskDTO.getDueDate());

        Task updatedTask = taskRepo.save(task);
        return TaskMapper.INSTANCE.toTaskDTO(updatedTask);
    }

    @Override
    public void deleteTask(Long taskId) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));

        // Delete comments associated with the task first
        commentService.deleteCommentsByTaskId(taskId);

        // Now delete the task
        taskRepo.delete(task);
    }

    @Override
    public List<TaskDTO> getTasksByProjectId(long projectId) {
        List<Task> tasks = taskRepo.findByProjectProjectId(projectId);
        return tasks.stream()
                .map(TaskMapper.INSTANCE::toTaskDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getTasksByUserId(long userId) {
        List<Task> tasks = taskRepo.findByAssignedToId(userId);
        return tasks.stream()
                .map(TaskMapper.INSTANCE::toTaskDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getTasksByBoardId(long boardId) {
        List<Task> tasks = taskRepo.findByBoardBoardId(boardId);
        return tasks.stream()
                .map(TaskMapper.INSTANCE::toTaskDTO)
                .collect(Collectors.toList());
    }

    // New method for retrieving tasks by due date
    @Override
    public List<TaskDTO> getTasksByDueDateBefore(LocalDateTime dateTime) {
        List<Task> tasks = taskRepo.findByDueDateBefore(dateTime);
        return tasks.stream()
                .map(TaskMapper.INSTANCE::toTaskDTO)
                .collect(Collectors.toList());
    }
}
