package org.example.snsx.config;

import org.example.snsx.domain.user.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UserException.class)
  public ResponseEntity<ErrorResponse> handleUserException(UserException e) {
    HttpStatus status =
        switch (e.getErrorCode()) {
          case USER_NOT_FOUND -> HttpStatus.NOT_FOUND;
          case USERNAME_ALREADY_EXISTS, EMAIL_ALREADY_EXISTS -> HttpStatus.CONFLICT;
        };
    return ResponseEntity.status(status)
        .body(new ErrorResponse(e.getErrorCode().name(), e.getMessage()));
  }

  public record ErrorResponse(String code, String message) {}
}
