package bzu.gradproj.optivet.backend.controller.auth.v1;

import bzu.gradproj.optivet.backend.dto.auth.*;
import bzu.gradproj.optivet.backend.model.entity.Client;
import bzu.gradproj.optivet.backend.model.entity.User;
//import bzu.gradproj.optivet.backend.service.auth.AuthService;
import bzu.gradproj.optivet.backend.service.auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth") // Add this annotation to handle routing
public class AuthenticationControllerV1 extends BaseController {

  @Value("${javatab.token.header}")
  private String tokenHeader;

  private final AuthService authenticationService;

  @PostMapping()
  public ResponseEntity<AuthResponse> authenticationRequest(@RequestBody @Valid AuthRequest authRequest) {
    return ResponseEntity.ok(this.authenticationService.authenticate(authRequest));
  }

  @GetMapping("/refresh")
  public ResponseEntity<AuthResponse> authenticationRequest(HttpServletRequest request) {
    return ResponseEntity.ok(this.authenticationService.refreshToken(request.getHeader(tokenHeader)));
  }

  @PostMapping("/register")
  public ResponseEntity<Client> registerUser(@RequestBody @Valid RegisterRequest registerRequest) {
    Client createdUser= this.authenticationService.registerUser(registerRequest);
    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
  }

  @PostMapping("/register-employee")
  public ResponseEntity<User> registerEmployee(@RequestBody @Valid RegisterEmployeeRequest registerEmployeeRequest) {
    User createdEmployee = this.authenticationService.registerEmployee(registerEmployeeRequest);
    return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
  }

  @PostMapping("/password-reset")
  public ResponseEntity<Void> requestPasswordReset(@RequestBody @Valid PasswordResetRequest passwordResetRequest) {
    authenticationService.requestPasswordReset(passwordResetRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/password-reset/confirm")
  public ResponseEntity<Void> confirmPasswordReset(@RequestBody @Valid PasswordResetConfirmRequest passwordResetConfirmRequest) {
    authenticationService.confirmPasswordReset(passwordResetConfirmRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
