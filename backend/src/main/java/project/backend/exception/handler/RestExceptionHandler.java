package project.backend.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import project.backend.exception.response.ExceptionResponse;
import project.backend.exception.types.BadRequestException;
import project.backend.exception.types.ResourceNotFoundException;

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

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException ex) {
        return new ResponseEntity<>(new ExceptionResponse(400, "Bad Request", ex.getMessage(), new Timestamp(System.currentTimeMillis())), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(new ExceptionResponse(400, "Bad Request", ex.getMessage(), new Timestamp(System.currentTimeMillis())), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception ex) {
        return new ResponseEntity<>(new ExceptionResponse(500, "Internal Server Error", ex.getMessage(), new Timestamp(System.currentTimeMillis())), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
