package project.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.Timestamp;

@RestController
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new ExceptionResponse(404, "Not Found", ex.getMessage(), new Timestamp(System.currentTimeMillis())), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoHandlerFoundException(NoResourceFoundException ex) {
        return new ResponseEntity<>(new ExceptionResponse(404, "Not Found", ex.getMessage(), new Timestamp(System.currentTimeMillis())), HttpStatus.NOT_FOUND);
    }

}
