package org.example.snsx.domain.user;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public User register(String username, String email, String password, String nickname) {
    if (userRepository.existsByUsername(username)) {
      throw new UserException(UserException.ErrorCode.USERNAME_ALREADY_EXISTS);
    }
    if (userRepository.existsByEmail(email)) {
      throw new UserException(UserException.ErrorCode.EMAIL_ALREADY_EXISTS);
    }

    String encodedPassword = passwordEncoder.encode(password);
    User user = new User(username, email, encodedPassword, nickname);
    return userRepository.save(user);
  }

  @Transactional
  public User update(Long id, String nickname, String password) {
    User user = findById(id);
    String encodedPassword = passwordEncoder.encode(password);
    user.updateProfile(nickname, encodedPassword);
    return user;
  }

  public User findById(Long id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new UserException(UserException.ErrorCode.USER_NOT_FOUND));
  }

  public User findByUsername(String username) {
    return userRepository
        .findByUsername(username)
        .orElseThrow(() -> new UserException(UserException.ErrorCode.USER_NOT_FOUND));
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }
}
