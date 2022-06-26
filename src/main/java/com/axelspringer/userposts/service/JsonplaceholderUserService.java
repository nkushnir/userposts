package com.axelspringer.userposts.service;

import com.axelspringer.userposts.error.UserNotFoundException;
import com.axelspringer.userposts.model.User;
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
    User user =
        new User(
            10000L,
            "Leanne Graham",
            "Bret",
            "Sincere@april.biz",
            new User.Address(
                "Kulas Light",
                "Apt. 556",
                "Gwenborough",
                "92998-3874",
                new User.Address.Geo(-37.3159, 81.1496)),
            "1-770-736-8031 x56442",
            "hildegard.org",
            new User.Company(
                "Romaguera-Crona",
                "Multi-layered client-server neural-net",
                "harness real-time e-markets"));

    //    return Mono.just(user);

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
