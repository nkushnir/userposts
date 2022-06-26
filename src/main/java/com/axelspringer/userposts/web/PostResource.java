package com.axelspringer.userposts.web;

import com.axelspringer.userposts.model.Post;
import com.axelspringer.userposts.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/posts")
public class PostResource {

  private final PostService postService;

  public PostResource(PostService postService) {
    this.postService = postService;
  }

  @GetMapping
  Flux<Post> getUserPosts(@RequestParam Long userId) {
    return postService.getUserPosts(userId);
  }
}
