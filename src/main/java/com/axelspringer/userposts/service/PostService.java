package com.axelspringer.userposts.service;

import com.axelspringer.userposts.dto.Post;
import reactor.core.publisher.Flux;

public interface PostService {
  Flux<Post> getUserPosts(Long userId);
}
