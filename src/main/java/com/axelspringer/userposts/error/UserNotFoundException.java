package com.axelspringer.userposts.error;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(Long userId) {
    super("Can't find user with id: %s".formatted(userId));
  }
}
