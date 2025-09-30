package br.com.izilearn.izilearn_application.infrastructure.web.exception;

import br.com.izilearn.izilearn_application.application.usecase.user.exception.UserNotFoundException;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e, WebRequest request) {
        ErrorResponse response = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                "User not found",
                LocalDateTime.now(),
                request.getDescription(false)
                        .replace("uri=", "")
                        .replace("%40", "@")
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
