package org.example.snsx.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 50)
  private String username;

  @Column(nullable = false, unique = true, length = 100)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(length = 100)
  private String nickname;

  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  public User(String username, String email, String password, String nickname) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.nickname = nickname;
    this.createdAt = LocalDateTime.now();
  }

  public void updateProfile(String nickname, String password) {
    this.nickname = nickname;
    this.password = password;
    this.updatedAt = LocalDateTime.now();
  }
}
