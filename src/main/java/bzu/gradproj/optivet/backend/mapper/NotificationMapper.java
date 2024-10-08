package bzu.gradproj.optivet.backend.mapper;

import bzu.gradproj.optivet.backend.dto.NotificationDTO;
import bzu.gradproj.optivet.backend.model.entity.Notification;
import bzu.gradproj.optivet.backend.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    @Mappings({
            @Mapping(source = "sender.id", target = "senderId"),
            @Mapping(source = "recipient.id", target = "recipientId")
    })
    NotificationDTO toNotificationDTO(Notification notification);

    @Mappings({
            @Mapping(source = "senderId", target = "sender.id"),
            @Mapping(source = "recipientId", target = "recipient.id")
    })
    Notification toNotificationEntity(NotificationDTO notificationDTO);

    default Long map(User user) {
        return user == null ? null : user.getId();
    }

    default User map(Long userId) {
        if (userId == null) {
            return null;
        }
        User user = new User();
        user.setId(userId);
        return user;
    }
}
