package bzu.gradproj.optivet.backend.service.impl;

import bzu.gradproj.optivet.backend.dto.AttachmentDTO;
import bzu.gradproj.optivet.backend.exception.ResourceNotFoundException;
import bzu.gradproj.optivet.backend.mapper.AttachmentMapper;
import bzu.gradproj.optivet.backend.model.entity.Attachment;
import bzu.gradproj.optivet.backend.model.entity.Task;
import bzu.gradproj.optivet.backend.model.entity.User;
import bzu.gradproj.optivet.backend.repository.AttachmentRepo;
import bzu.gradproj.optivet.backend.repository.TaskRepo;
import bzu.gradproj.optivet.backend.repository.UserRepo;
import bzu.gradproj.optivet.backend.service.AttachmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentRepo attachmentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TaskRepo taskRepo;

    @Override
    public AttachmentDTO createAttachment(AttachmentDTO attachmentDTO) {
        User user = userRepo.findById(attachmentDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + attachmentDTO.getUserId()));
        Task task = taskRepo.findById(attachmentDTO.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + attachmentDTO.getTaskId()));

        Attachment attachment = AttachmentMapper.mapToAttachmentEntity(attachmentDTO, user, task);
        Attachment attachmentSave = attachmentRepo.save(attachment);

        return AttachmentMapper.mapToAttachmentDto(attachmentSave);
    }

    @Override
    public AttachmentDTO getAttachmentById(Long attachmentId) {
        Attachment attachment = attachmentRepo.findById(attachmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Attachment not found with id: " + attachmentId));
        return AttachmentMapper.mapToAttachmentDto(attachment);
    }

    @Override
    public List<AttachmentDTO> getAllAttachments() {
        List<Attachment> attachments = attachmentRepo.findAll();
        return attachments.stream().map(AttachmentMapper::mapToAttachmentDto).collect(Collectors.toList());
    }

    @Override
    public List<AttachmentDTO> getAttachmentsByTaskId(Long taskId) {
        taskRepo.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));
        List<Attachment> attachments = attachmentRepo.findAttachmentByTaskTaskId(taskId);
        return attachments.stream().map(AttachmentMapper::mapToAttachmentDto).collect(Collectors.toList());
    }

    @Override
    public AttachmentDTO updateAttachment(Long attachmentId, AttachmentDTO updateAttachmentDTO) {
        User user = userRepo.findById(updateAttachmentDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + updateAttachmentDTO.getUserId()));
        Task task = taskRepo.findById(updateAttachmentDTO.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + updateAttachmentDTO.getTaskId()));
        Attachment attachment = attachmentRepo.findById(attachmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Attachment not found with id: " + attachmentId));

        attachment.setUser(user);
        attachment.setTask(task);
//        attachment.setCreatedAt(updateAttachmentDTO.getCreatedAt());
        attachment.setFileURL(updateAttachmentDTO.getFileURL());
        attachment.setFileName(updateAttachmentDTO.getFileName());

        Attachment savedAttachment = attachmentRepo.save(attachment);
        return AttachmentMapper.mapToAttachmentDto(savedAttachment);
    }

    @Override
    public void deleteAttachment(Long attachmentId) {
        Attachment attachment = attachmentRepo.findById(attachmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Attachment not found with id: " + attachmentId));
        attachmentRepo.delete(attachment);
    }
}
