package org.example.snsx.domain.user;

public class UserException extends RuntimeException {

  private final ErrorCode errorCode;

  public UserException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }

  public enum ErrorCode {
    USER_NOT_FOUND("User not found"),
    USERNAME_ALREADY_EXISTS("Username already exists"),
    EMAIL_ALREADY_EXISTS("Email already exists");

    private final String message;

    ErrorCode(String message) {
      this.message = message;
    }

    public String getMessage() {
      return message;
    }
  }
}
