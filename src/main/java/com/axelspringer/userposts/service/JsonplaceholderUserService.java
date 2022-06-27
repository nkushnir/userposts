package com.axelspringer.userposts.service;

import com.axelspringer.userposts.dto.User;
import com.axelspringer.userposts.error.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class JsonplaceholderUserService implements UserService {

  private final WebClient webClient;

  public JsonplaceholderUserService(WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public Mono<User> getUser(Long id) {
    return webClient
        .get()
        .uri("/users/{id}", id)
        .exchangeToMono(
            response -> {
              if (response.statusCode().is4xxClientError()) {
                return Mono.error(new UserNotFoundException(id));
              }
              return response.bodyToMono(User.class);
            });
  }
}
