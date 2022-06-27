package com.axelspringer.userposts.web;

import com.axelspringer.userposts.dto.UserDetails;
import com.axelspringer.userposts.service.PostService;
import com.axelspringer.userposts.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user-details/{id}")
public class UserDetailsResource {

  private final UserService userService;
  private final PostService postService;

  public UserDetailsResource(UserService userService, PostService postService) {
    this.userService = userService;
    this.postService = postService;
  }

  @GetMapping
  Mono<UserDetails> getUserDetails(@PathVariable Long id) {
    return userService
        .getUser(id)
        .zipWhen(
            u -> postService.getUserPosts(u.id()).collectList(), (u, p) -> UserDetails.of(u, p));
  }
}
