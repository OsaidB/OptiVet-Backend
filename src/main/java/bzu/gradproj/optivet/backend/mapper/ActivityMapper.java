package bzu.gradproj.optivet.backend.mapper;

import bzu.gradproj.optivet.backend.dto.ActivityDTO;
import bzu.gradproj.optivet.backend.model.entity.Activity;
import bzu.gradproj.optivet.backend.model.entity.Task;
import bzu.gradproj.optivet.backend.model.entity.User;

public class ActivityMapper {
    public static ActivityDTO mapToActivityDTO(Activity activity) {
        return new ActivityDTO(
                activity.getActivityId(),
                activity.getUser().getId(),
//                activity.getProject().getProjectId(),
                activity.getTask().getTaskId(),
                activity.getAction(),
                activity.getCreatedAt()
        );
    }
    public static Activity mapToActivityEntity(ActivityDTO activityDTO, User user,Task task) {
        return new Activity(
                activityDTO.getActivityId(),
                user,
//                project,
                task,
                activityDTO.getAction(),
                activityDTO.getCreatedAt()
        );
    }
}
