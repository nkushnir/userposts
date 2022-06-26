package com.axelspringer.userposts.web;

import com.axelspringer.userposts.model.User;
import com.axelspringer.userposts.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users/{id}")
public class UserResource {

  private final UserService userService;

  public UserResource(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  Mono<User> getUser(@PathVariable Long id) {
    return userService.getUser(id);
  }
}
