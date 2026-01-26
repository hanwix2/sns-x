package org.example.snsx.controller.dto;

import org.example.snsx.domain.user.User;

public record UserRegisterRequest(String username, String email, String password, String nickname) {

  public User toEntity() {
    return new User(username, email, password, nickname);
  }
}
