package uz.pdp.telegraphbackend.exceptions;

import org.postgresql.util.PSQLException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = AuthenticationFailedException.class)
    private ResponseEntity<String> authenticationFailedExceptionHandler(
            AuthenticationFailedException e
    ){
        return ResponseEntity.status(401).body(e.getMessage());
    }

    @ExceptionHandler(value = {RequestValidationException.class})
    public ResponseEntity<String> requestValidationExceptionHandler(
            RequestValidationException e
    ) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(value = {PSQLException.class})
    public ResponseEntity<String> PSQLExceptionHandler(
            PSQLException ignored
    ){
        return ResponseEntity.status(400).body("Username already Exists");
    }
}
