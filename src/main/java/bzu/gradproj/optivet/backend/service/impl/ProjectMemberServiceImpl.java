package bzu.gradproj.optivet.backend.service.impl;

import bzu.gradproj.optivet.backend.dto.ProjectMemberDTO;
import bzu.gradproj.optivet.backend.exception.ResourceNotFoundException;
import bzu.gradproj.optivet.backend.mapper.ProjectMemberMapper;
import bzu.gradproj.optivet.backend.model.entity.Project;
import bzu.gradproj.optivet.backend.model.entity.ProjectMember;
import bzu.gradproj.optivet.backend.model.entity.User;
import bzu.gradproj.optivet.backend.repository.ProjectMemberRepo;
import bzu.gradproj.optivet.backend.repository.ProjectRepo;
import bzu.gradproj.optivet.backend.repository.UserRepo;
import bzu.gradproj.optivet.backend.service.ProjectMemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {

    @Autowired
    private ProjectMemberRepo projectMemberRepo;

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private UserRepo userRepo;

//    @Autowired
//    private ProjectMemberMapper projectMemberMapper;
    @Override
    public ProjectMemberDTO addProjectMember(ProjectMemberDTO projectMemberDTO) {
        Project project = projectRepo.findById(projectMemberDTO.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectMemberDTO.getProjectId()));

        User user = userRepo.findById(projectMemberDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + projectMemberDTO.getUserId()));

        // Check if the user is already a member of the project
        boolean exists = projectMemberRepo.existsByProjectProjectIdAndUserId(projectMemberDTO.getProjectId(), projectMemberDTO.getUserId());
        if (exists) {
            throw new IllegalArgumentException("User is already a member of the project.");
        }

        ProjectMember projectMember = ProjectMemberMapper.INSTANCE.toProjectMemberEntity(projectMemberDTO);
        ProjectMember savedProjectMember = projectMemberRepo.save(projectMember);

        return ProjectMemberMapper.INSTANCE.toProjectMemberDTO(savedProjectMember);
    }

    @Override
    public ProjectMemberDTO getProjectMemberById(Long projectMemberId) {
        ProjectMember projectMember = projectMemberRepo.findById(projectMemberId)
                .orElseThrow(() -> new ResourceNotFoundException("Project member not found with id: " + projectMemberId));

        return ProjectMemberMapper.INSTANCE.toProjectMemberDTO(projectMember);
    }

    @Override
    public List<ProjectMemberDTO> getAllProjectMembers() {
        List<ProjectMember> projectMembers = projectMemberRepo.findAll();
        return projectMembers.stream()
                .map(ProjectMemberMapper.INSTANCE::toProjectMemberDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectMemberDTO updateProjectMember(Long projectMemberId, ProjectMemberDTO updatedProjectMemberDTO) {
        ProjectMember projectMember = projectMemberRepo.findById(projectMemberId)
                .orElseThrow(() -> new ResourceNotFoundException("Project member not found with id: " + projectMemberId));

        // No role field anymore, update only joinedAt if needed
//        projectMember.setJoinedAt(updatedProjectMemberDTO.getJoinedAt());

        ProjectMember updatedProjectMember = projectMemberRepo.save(projectMember);
        return ProjectMemberMapper.INSTANCE.toProjectMemberDTO(updatedProjectMember);
    }

    @Override
    public void deleteProjectMember(Long projectMemberId) {
        ProjectMember projectMember = projectMemberRepo.findById(projectMemberId)
                .orElseThrow(() -> new ResourceNotFoundException("Project member not found with id: " + projectMemberId));
        projectMemberRepo.delete(projectMember);
    }

    @Override
    public void deleteProjectMemberFromProject(Long projectId, Long projectMemberId) {
        ProjectMember projectMember = projectMemberRepo.findById(projectMemberId)
                .orElseThrow(() -> new ResourceNotFoundException("Project member not found with id: " + projectMemberId));

        if (!projectMember.getProject().getProjectId().equals(projectId)) {
            throw new ResourceNotFoundException("Project member not found in the specified project.");
        }

        projectMemberRepo.delete(projectMember);
    }

    @Override
    public List<ProjectMemberDTO> getMembersByProjectId(Long projectId) {
        List<ProjectMember> projectMembers = projectMemberRepo.findByProjectProjectId(projectId);
        return projectMembers.stream()
                .map(ProjectMemberMapper.INSTANCE::toProjectMemberDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProjectMemberFromAllProjects(Long userId) {
        List<ProjectMember> projectMembers = projectMemberRepo.findByUserId(userId);
        if (projectMembers.isEmpty()) {
            throw new ResourceNotFoundException("Project member not found with user id: " + userId);
        }
        projectMemberRepo.deleteAll(projectMembers);
    }
}
