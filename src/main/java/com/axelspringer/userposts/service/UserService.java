package com.axelspringer.userposts.service;

import com.axelspringer.userposts.model.User;
import reactor.core.publisher.Mono;

public interface UserService {
  Mono<User> getUser(Long id);
}
