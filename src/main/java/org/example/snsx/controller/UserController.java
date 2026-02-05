package org.example.snsx.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.snsx.controller.dto.UserRegisterRequest;
import org.example.snsx.controller.dto.UserResponse;
import org.example.snsx.controller.dto.UserUpdateRequest;
import org.example.snsx.domain.user.User;
import org.example.snsx.domain.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/api/v1/users")
  public ResponseEntity<UserResponse> register(@RequestBody UserRegisterRequest request) {
    User user =
        userService.register(
            request.username(), request.email(), request.password(), request.nickname());
    return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.from(user));
  }

  @GetMapping("/api/v1/users/{id}")
  public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
    User user = userService.findById(id);
    return ResponseEntity.ok(UserResponse.from(user));
  }

  @PutMapping("/api/v1/users/{id}")
  public ResponseEntity<UserResponse> update(
      @PathVariable Long id, @RequestBody UserUpdateRequest request) {
    User user = userService.update(id, request.nickname(), request.password());
    return ResponseEntity.ok(UserResponse.from(user));
  }

  @GetMapping("/api/v1/users/username/{username}")
  public ResponseEntity<UserResponse> findByUsername(@PathVariable String username) {
    User user = userService.findByUsername(username);
    return ResponseEntity.ok(UserResponse.from(user));
  }

  @GetMapping("/api/v1/users")
  public ResponseEntity<List<UserResponse>> findAll() {
    List<UserResponse> users = userService.findAll().stream().map(UserResponse::from).toList();
    return ResponseEntity.ok(users);
  }
}
