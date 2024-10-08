package bzu.gradproj.optivet.backend.service.impl;

import bzu.gradproj.optivet.backend.dto.ProjectDTO;
import bzu.gradproj.optivet.backend.exception.ResourceNotFoundException;
import bzu.gradproj.optivet.backend.mapper.ProjectMapper;
import bzu.gradproj.optivet.backend.model.entity.Board;
import bzu.gradproj.optivet.backend.model.entity.Project;
import bzu.gradproj.optivet.backend.model.entity.ProjectMember;
import bzu.gradproj.optivet.backend.model.entity.Task;
import bzu.gradproj.optivet.backend.repository.BoardRepo;
import bzu.gradproj.optivet.backend.repository.ProjectMemberRepo;
import bzu.gradproj.optivet.backend.repository.ProjectRepo;
import bzu.gradproj.optivet.backend.repository.TaskRepo;
import bzu.gradproj.optivet.backend.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private BoardRepo boardRepo;

    @Autowired
    private ProjectMemberRepo projectMemberRepo;

    @Autowired
    private TaskRepo taskRepo;

    @Override
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project project = ProjectMapper.INSTANCE.toProjectEntity(projectDTO);
        Project savedProject = projectRepo.save(project);
        return ProjectMapper.INSTANCE.toProjectDTO(savedProject);
    }

    @Override
    public ProjectDTO getProjectById(Long projectId) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));
        return ProjectMapper.INSTANCE.toProjectDTO(project);
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = projectRepo.findAll();
        return projects.stream()
                .map(ProjectMapper.INSTANCE::toProjectDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDTO updateProject(Long projectId, ProjectDTO updatedProject) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));

        project.setName(updatedProject.getName());
        project.setDescription(updatedProject.getDescription());
        project.setUpdatedAt(updatedProject.getUpdatedAt());

        Project updatedProjectEntity = projectRepo.save(project);
        return ProjectMapper.INSTANCE.toProjectDTO(updatedProjectEntity);
    }

    @Transactional // Ensure that the delete operation is transactional
    @Override
    public void deleteProject(Long projectId) {
        // Find the project by ID
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));

        // Find all boards associated with the project
        List<Board> boards = boardRepo.findByProjectProjectId(projectId);
        if (!boards.isEmpty()) {
            // Loop through each board to delete related tasks
            for (Board board : boards) {
                // Delete tasks associated with each board
                List<Task> tasks = taskRepo.findByBoardBoardId(board.getBoardId());
                if (!tasks.isEmpty()) {
                    taskRepo.deleteAll(tasks);
                }
            }
            // Now delete all boards
            boardRepo.deleteAll(boards);
        }

        // Find and delete all project members associated with the project
        List<ProjectMember> projectMembers = projectMemberRepo.findByProjectProjectId(projectId);
        if (!projectMembers.isEmpty()) {
            projectMemberRepo.deleteAll(projectMembers);
        }

        // Finally, delete the project itself
        projectRepo.delete(project);
    }
}
