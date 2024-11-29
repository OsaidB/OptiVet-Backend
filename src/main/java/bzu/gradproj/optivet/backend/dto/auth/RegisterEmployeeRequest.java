package bzu.gradproj.optivet.backend.dto.auth;

import bzu.gradproj.optivet.backend.model.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Represents a request to register a new employee (e.g., Vet Assistant, Secretary, or Manager).
 * Includes fields for personal details, contact information, and role assignment.
 */
@Getter
@Setter
public class RegisterEmployeeRequest extends ModelBase {

    private static final long serialVersionUID = 7151443507829405471L;

    @Email(message = "A valid email address must be provided")
    @NotNull(message = "Email must be provided")
    private String email;

    @NotNull(message = "Password must be provided")
    private String password;

    @NotNull(message = "First name must be provided")
    private String firstName;

    @NotNull(message = "Last name must be provided")
    private String lastName;

    @NotNull(message = "Phone number must be provided")
    private String phoneNumber;

    @NotNull(message = "Date of birth must be provided")
    private LocalDate dateOfBirth;

    @NotNull(message = "Role must be provided")
    private User.UserRole role; // Enum for roles (e.g., VET_ASSISTANT, SECRETARY, MANAGER)
}
