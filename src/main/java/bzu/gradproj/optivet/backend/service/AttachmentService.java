package bzu.gradproj.optivet.backend.service;

import bzu.gradproj.optivet.backend.dto.AttachmentDTO;
import java.util.List;

public interface AttachmentService {
    AttachmentDTO createAttachment(AttachmentDTO attachmentDTO);
    AttachmentDTO getAttachmentById(Long attachmentId);
    List<AttachmentDTO> getAllAttachments();
    List<AttachmentDTO> getAttachmentsByTaskId(Long taskId);
    AttachmentDTO updateAttachment(Long attachmentId , AttachmentDTO attachmentDTO);
    void deleteAttachment(Long attachmentId);
}
