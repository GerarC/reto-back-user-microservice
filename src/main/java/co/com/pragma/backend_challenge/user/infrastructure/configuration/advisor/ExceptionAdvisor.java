package co.com.pragma.backend_challenge.user.infrastructure.configuration.advisor;

import co.com.pragma.backend_challenge.user.domain.exception.*;
import co.com.pragma.backend_challenge.user.domain.util.annotation.Generated;
import co.com.pragma.backend_challenge.user.infrastructure.configuration.advisor.response.ExceptionResponse;
import co.com.pragma.backend_challenge.user.infrastructure.configuration.advisor.response.ValidationExceptionResponse;
import co.com.pragma.backend_challenge.user.infrastructure.util.ExceptionResponseBuilder;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Generated
@ControllerAdvice
public class ExceptionAdvisor {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFound(EntityNotFoundException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleEntityAlreadyExists(EntityAlreadyExistsException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnderAgedUserException.class)
    public ResponseEntity<ExceptionResponse> handleUnderAgedUser(UnderAgedUserException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotRegisteredException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotRegistered(UserNotRegisteredException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<ExceptionResponse> handleExpiredToken(ExpiredTokenException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidToken(InvalidTokenException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler( MethodArgumentNotValidException.class )
    public ResponseEntity<ValidationExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ValidationExceptionResponse exceptionResponse = ValidationExceptionResponse.builder()
                .statusCode(e.getStatusCode().value())
                .status(HttpStatus.resolve(e.getStatusCode().value()))
                .timestamp(LocalDateTime.now())
                .errors(e.getFieldErrors().stream().map(field -> {
                    StringBuilder sb = new StringBuilder();
                    String rejectedValue = field.getRejectedValue() == null ? "null" : field.getRejectedValue().toString();
                    sb.append(field.getDefaultMessage()).append(": ").append(rejectedValue);
                    return sb.toString();
                }).toList())
                .message(e.getBody().getDetail()).build();
        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);
    }
}
