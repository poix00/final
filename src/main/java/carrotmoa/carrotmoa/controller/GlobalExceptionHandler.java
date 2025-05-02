package carrotmoa.carrotmoa.controller;

import carrotmoa.carrotmoa.exception.UserServiceException;
import carrotmoa.carrotmoa.model.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserServiceException.class)
    public ResponseEntity<ErrorResponse> handleUserWithdrawalException(UserServiceException e) {
        ErrorResponse response =  new ErrorResponse("UserServiceError",e.getMessage(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }
}
