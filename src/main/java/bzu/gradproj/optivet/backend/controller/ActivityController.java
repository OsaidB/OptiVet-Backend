package bzu.gradproj.optivet.backend.controller;

import bzu.gradproj.optivet.backend.dto.ActivityDTO;
import bzu.gradproj.optivet.backend.service.ActivityService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/activities")
public class ActivityController {
    @Autowired
    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityDTO> createActivity(@Valid @RequestBody ActivityDTO activityDTO) {
        ActivityDTO createActivity = activityService.createActivity(activityDTO);
        return new ResponseEntity<>(createActivity, HttpStatus.CREATED);
    }

    @GetMapping("{activityId}")
    public ResponseEntity<ActivityDTO> getActivityById(@PathVariable("activityId") long activityId) {
        ActivityDTO getActivityById = activityService.getActivityById(activityId);
        return ResponseEntity.ok(getActivityById);
    }

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<List<ActivityDTO>> getActivityByTaskId(@PathVariable("taskId") long taskId) {
        List<ActivityDTO> activityDTO = activityService.getActivitiesByTaskId(taskId);
        return ResponseEntity.ok(activityDTO);
    }

    @GetMapping
    public ResponseEntity<List<ActivityDTO>> getAllActivities() {
        List<ActivityDTO> allActivities = activityService.getAllActivities();
        return ResponseEntity.ok(allActivities);
    }

    @PutMapping("{activityId}")
    public ResponseEntity<ActivityDTO> updateActivity(@PathVariable("activityId") long activityId
                                                        ,@Valid @RequestBody ActivityDTO updateActivityDTO) {
        ActivityDTO activityDTO = activityService.updateActivity(activityId, updateActivityDTO);
        return ResponseEntity.ok(activityDTO);
    }

    @DeleteMapping("{activityId}")
    public ResponseEntity<String> deleteActivity(@PathVariable("activityId") long activityId) {
        activityService.deleteActivity(activityId);
        return ResponseEntity.ok("Activity Deleted successfully");
    }
}
