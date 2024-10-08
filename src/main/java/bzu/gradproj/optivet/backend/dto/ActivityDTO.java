package bzu.gradproj.optivet.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDTO {
    private Long activityId;

    @NotNull(message = "User ID cannot be null")
    private Long userId;


    @NotNull(message = "Task ID cannot be null")
    private Long taskId;

//    @Size(max = 255, message = "Action must be less than 255 characters")
//    @Pattern(regexp = "create|update|delete", message = "Action must be one of 'create', 'update', 'delete'")
    @NotNull(message = "Action cannot be null")
    private String action;

    private LocalDateTime createdAt;
}
