package bzu.gradproj.optivet.backend.service.impl;

import bzu.gradproj.optivet.backend.dto.ActivityDTO;
import bzu.gradproj.optivet.backend.exception.ResourceNotFoundException;
import bzu.gradproj.optivet.backend.mapper.ActivityMapper;
import bzu.gradproj.optivet.backend.model.entity.Activity;
import bzu.gradproj.optivet.backend.model.entity.Task;
import bzu.gradproj.optivet.backend.model.entity.User;
import bzu.gradproj.optivet.backend.repository.ActivityRepo;
import bzu.gradproj.optivet.backend.repository.TaskRepo;
import bzu.gradproj.optivet.backend.repository.UserRepo;
import bzu.gradproj.optivet.backend.service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepo activityRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TaskRepo taskRepo;


    @Override
    public ActivityDTO createActivity(ActivityDTO activityDTO) {
        User user = userRepo.findById(activityDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found" + activityDTO.getUserId()));
        Task task = taskRepo.findById(activityDTO.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task Not Found" + activityDTO.getTaskId()));


        Activity activity = ActivityMapper.mapToActivityEntity(activityDTO, user, task);
        Activity savedActivity = activityRepo.save(activity);
        return ActivityMapper.mapToActivityDTO(savedActivity);
    }

    @Override
    public ActivityDTO getActivityById(Long activityId) {
        Activity activity = activityRepo.findById(activityId)
                .orElseThrow(() -> new ResourceNotFoundException("Activity Not Found" + activityId));
        return ActivityMapper.mapToActivityDTO(activity);
    }

    @Override
    public List<ActivityDTO> getActivitiesByTaskId(Long taskId) {
        taskRepo.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task Not Found" + taskId));
        List<Activity> activities = activityRepo.getActivitiesByTaskTaskId(taskId);
        return activities.stream().map(ActivityMapper::mapToActivityDTO).collect(Collectors.toList());
    }

    @Override
    public List<ActivityDTO> getAllActivities() {
        List<Activity> activities = activityRepo.findAll();
        return activities.stream().map(ActivityMapper::mapToActivityDTO).collect(Collectors.toList());
    }

    @Override
    public ActivityDTO updateActivity(Long activityId, ActivityDTO updateActivityDTO) {
        User user = userRepo.findById(updateActivityDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found" + updateActivityDTO.getUserId()));
        Task task = taskRepo.findById(updateActivityDTO.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task Not Found" + updateActivityDTO.getTaskId()));
        Activity activity = activityRepo.findById(activityId)
                .orElseThrow(() -> new ResourceNotFoundException("Activity Not Found" + activityId));

        activity.setAction(updateActivityDTO.getAction());
        activity.setUser(user);
        activity.setTask(task);
//        activity.setCreatedAt(updateActivityDTO.getCreatedAt());

        Activity savedActivity = activityRepo.save(activity);
        return ActivityMapper.mapToActivityDTO(savedActivity);
    }

    @Override
    public void deleteActivity(Long activityId) {
        Activity activity = activityRepo.findById(activityId)
                .orElseThrow(() -> new ResourceNotFoundException("Activity Not Found" + activityId));
        activityRepo.delete(activity);
    }
}
