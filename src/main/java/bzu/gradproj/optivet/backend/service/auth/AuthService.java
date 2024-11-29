package bzu.gradproj.optivet.backend.service.auth;


import bzu.gradproj.optivet.backend.dto.auth.AuthRequest;
import bzu.gradproj.optivet.backend.dto.auth.AuthResponse;
import bzu.gradproj.optivet.backend.model.entity.Client;
import bzu.gradproj.optivet.backend.model.entity.User;
import bzu.gradproj.optivet.backend.dto.auth.RegisterRequest;
import bzu.gradproj.optivet.backend.dto.auth.RegisterEmployeeRequest; // Import the new DTO
import bzu.gradproj.optivet.backend.dto.auth.PasswordResetRequest;
import bzu.gradproj.optivet.backend.dto.auth.PasswordResetConfirmRequest;

public interface AuthService {
    AuthResponse authenticate(AuthRequest authRequest);

    AuthResponse refreshToken(String token);

    Client registerUser(RegisterRequest registerRequest);

    User registerEmployee(RegisterEmployeeRequest registerEmployeeRequest); // New method for registering employees

    // New methods for password reset
    void requestPasswordReset(PasswordResetRequest passwordResetRequest);
//    void requestPasswordReset(String email);

    void confirmPasswordReset(PasswordResetConfirmRequest passwordResetConfirmRequest);
}
