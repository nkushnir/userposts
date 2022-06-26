package com.axelspringer.userposts.service;

import com.axelspringer.userposts.model.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class JsonplaceholderPostService implements PostService {

  private final WebClient webClient;

  public JsonplaceholderPostService(WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public Flux<Post> getUserPosts(Long userId) {
    return webClient.get().uri("/posts?userId={userId}", userId).retrieve().bodyToFlux(Post.class);
  }
}
