package bzu.gradproj.optivet.backend.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PasswordResetRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email")
    private String email;

//    // Constructors
//    public PasswordResetRequest() {}
//
//    public PasswordResetRequest(String email) {
//        this.email = email;
//    }
}