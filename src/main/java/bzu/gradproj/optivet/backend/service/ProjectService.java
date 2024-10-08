package bzu.gradproj.optivet.backend.service;

import bzu.gradproj.optivet.backend.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {
    ProjectDTO createProject(ProjectDTO projectDTO);
    ProjectDTO getProjectById(Long projectId);
    List<ProjectDTO> getAllProjects();
    ProjectDTO updateProject(Long projectId, ProjectDTO updatedProject);
    void deleteProject(Long projectId);
}
