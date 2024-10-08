package bzu.gradproj.optivet.backend.service.impl;

import bzu.gradproj.optivet.backend.model.entity.Task;
import bzu.gradproj.optivet.backend.model.entity.User;
//import bzu.gradproj.optivet.backend.repository.TaskRepo;
//import bzu.gradproj.optivet.backend.repository.UserRepo;
import bzu.gradproj.optivet.backend.repository.TaskRepo;
import bzu.gradproj.optivet.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskAssignmentService {

    @Autowired
    private TaskRepo taskRepository;

    @Autowired
    private UserRepo userRepository;

    public void assignTaskToUser(Task task, User assignedUser, User teamLeader) {
        // Check if the teamLeader is indeed a team leader for the role
        if (!teamLeader.getIsTeamLeader()) {
//            throw new UnauthorizedException("Only a team leader can assign tasks.");
        }

        // Check if the assignedUser is in the same role as the teamLeader
        if (!teamLeader.getFuncRole().equals(assignedUser.getFuncRole())) {
//            throw new UnauthorizedException("Team leader can only assign tasks to members of their team.");
        }

        // Assign the task to the user
        task.setAssignedTo(assignedUser);
        task.setStatus("Assigned");

        // Save the task
        taskRepository.save(task);

        // Log the assignment (optional)
        logTaskAssignment(task, assignedUser, teamLeader);
    }

    private void logTaskAssignment(Task task, User assignedUser, User teamLeader) {
        // Logic for logging task assignments
        System.out.println("Task " + task.getTaskName() + " assigned to " + assignedUser.getFirstName()+ assignedUser.getLastName() + " by " + teamLeader.getFirstName()+ teamLeader.getLastName());
    }
}
