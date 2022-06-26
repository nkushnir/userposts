package com.axelspringer.userposts.service;

import com.axelspringer.userposts.model.Post;
import reactor.core.publisher.Flux;

public interface PostService {
  Flux<Post> getUserPosts(Long userId);
}
