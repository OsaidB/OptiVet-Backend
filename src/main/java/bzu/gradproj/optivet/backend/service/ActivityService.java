package bzu.gradproj.optivet.backend.service;

import bzu.gradproj.optivet.backend.dto.ActivityDTO;
import java.util.List;

public interface ActivityService {
    ActivityDTO createActivity(ActivityDTO activityDTO);
    ActivityDTO getActivityById(Long activityId);
    List<ActivityDTO> getActivitiesByTaskId(Long taskId);
    List<ActivityDTO> getAllActivities();
    ActivityDTO updateActivity(Long activityId , ActivityDTO activityDTO);
    void deleteActivity(Long activityId);
}
