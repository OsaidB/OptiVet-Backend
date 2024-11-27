package bzu.gradproj.optivet.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class NoUserFoundAdvice {

    @ExceptionHandler(NoUserFoundException.class)
    public ResponseEntity<ApiErrorMessage> handleNoUserFoundException(NoUserFoundException ex, WebRequest request) {
        ApiErrorMessage message = ApiErrorMessage.builder()
//                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestamp(new Date())
                .message("Account with this email not found.") // User-friendly message
                .description("Please double-check the email or register a new account.") // Actionable description
                .build();
        // Return 200 OK with a custom response for better user experience
        return ResponseEntity.ok(message);
//        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND); // Still returning 404 for clarity in logs
    }
}
