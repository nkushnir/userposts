package com.axelspringer.userposts.web;

import static org.mockito.ArgumentMatchers.eq;

import com.axelspringer.userposts.dto.Post;
import com.axelspringer.userposts.dto.User;
import com.axelspringer.userposts.dto.UserDetails;
import com.axelspringer.userposts.error.UserNotFoundException;
import com.axelspringer.userposts.service.PostService;
import com.axelspringer.userposts.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = UserDetailsResource.class)
public class UserDetailsResourceTest {

  @Autowired private WebTestClient webTestClient;

  @MockBean private UserService userService;
  @MockBean private PostService postService;

  @Test
  public void testUserDetails() {
    Long userId = 10L;
    Mockito.when(userService.getUser(eq(userId))).thenReturn(Mono.just(user(userId)));
    Mockito.when(postService.getUserPosts(eq(userId)))
        .thenReturn(Flux.just(post(1L, userId), post(2L, userId)));
    webTestClient
        .get()
        .uri("/user-details/%d".formatted(userId))
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(UserDetails.class)
        .value(u -> u.username(), Matchers.equalTo("Username 10"))
        .value(u -> u.phone(), Matchers.equalTo("1-770-736-8031 x56442"))
        .value(u -> u.posts(), Matchers.hasSize(2));

    Mockito.verify(userService, Mockito.times(1)).getUser(userId);
    Mockito.verify(postService, Mockito.times(1)).getUserPosts(userId);
  }

  @Test
  public void testUserDetailsWithoutPosts() {
    Long userId = 10L;
    Mockito.when(userService.getUser(eq(userId))).thenReturn(Mono.just(user(userId)));
    Mockito.when(postService.getUserPosts(eq(userId))).thenReturn(Flux.empty());
    webTestClient
        .get()
        .uri("/user-details/%d".formatted(userId))
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(UserDetails.class)
        .value(u -> u.posts(), Matchers.hasSize(0));

    Mockito.verify(userService, Mockito.times(1)).getUser(userId);
    Mockito.verify(postService, Mockito.times(1)).getUserPosts(userId);
  }

  @Test
  public void testUserNotFound() {
    Long userId = 10L;
    Mockito.when(userService.getUser(eq(userId)))
        .thenReturn(Mono.error(new UserNotFoundException(1000L)));

    webTestClient
        .get()
        .uri("/user-details/%d".formatted(userId))
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isNotFound();

    Mockito.verify(userService, Mockito.times(1)).getUser(userId);
    Mockito.verify(postService, Mockito.never()).getUserPosts(userId);
  }

  private User user(Long id) {
    return new User(
        id,
        "User %d".formatted(id),
        "Username %d".formatted(id),
        "User%d@april.biz".formatted(id),
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
  }

  private Post post(Long id, Long userId) {
    return new Post(id, userId, "Post %d".formatted(id), "Post body %d".formatted(id));
  }
}
