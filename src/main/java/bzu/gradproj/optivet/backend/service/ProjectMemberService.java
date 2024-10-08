package bzu.gradproj.optivet.backend.service;

import bzu.gradproj.optivet.backend.dto.ProjectMemberDTO;

import java.util.List;

public interface ProjectMemberService {
    ProjectMemberDTO addProjectMember(ProjectMemberDTO projectMemberDTO);
    ProjectMemberDTO getProjectMemberById(Long projectMemberId);
    List<ProjectMemberDTO> getAllProjectMembers();

    ProjectMemberDTO updateProjectMember(Long projectMemberId, ProjectMemberDTO updatedProjectMemberDTO);

    void deleteProjectMember(Long projectMemberId);
    void deleteProjectMemberFromProject(Long projectId, Long projectMemberId);

    List<ProjectMemberDTO> getMembersByProjectId(Long projectId);

    void deleteProjectMemberFromAllProjects(Long userId);

}
