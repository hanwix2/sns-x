package org.example.snsx.controller.dto;

import java.time.LocalDateTime;
import org.example.snsx.domain.user.User;

public record UserResponse(
    Long id, String username, String email, String nickname, LocalDateTime createdAt) {

  public static UserResponse from(User user) {
    return new UserResponse(
        user.getId(), user.getUsername(), user.getEmail(), user.getNickname(), user.getCreatedAt());
  }
}
