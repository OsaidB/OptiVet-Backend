package bzu.gradproj.optivet.backend.mapper;

import bzu.gradproj.optivet.backend.dto.AttachmentDTO;
import bzu.gradproj.optivet.backend.model.entity.Attachment;
import bzu.gradproj.optivet.backend.model.entity.Task;
import bzu.gradproj.optivet.backend.model.entity.User;

public class AttachmentMapper {
    public static AttachmentDTO mapToAttachmentDto(Attachment attachment) {
        return new AttachmentDTO(
                attachment.getAttachmentId(),
                attachment.getTask().getTaskId(),
                attachment.getUser().getId(),
                attachment.getFileName(),
                attachment.getFileURL(),
                attachment.getCreatedAt()
        );
    }
    public static Attachment mapToAttachmentEntity(AttachmentDTO attachmentDTO, User user, Task task) {
        return new Attachment(
                attachmentDTO.getAttachmentId(),
                task,
                user,
                attachmentDTO.getFileName(),
                attachmentDTO.getFileURL(),
                attachmentDTO.getCreatedAt()
        );
    }
}
