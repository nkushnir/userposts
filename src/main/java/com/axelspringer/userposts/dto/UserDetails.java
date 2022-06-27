package com.axelspringer.userposts.dto;

import java.util.List;

public record UserDetails(
    Long id,
    String name,
    String username,
    String email,
    User.Address address,
    String phone,
    String website,
    User.Company company,
    List<Post> posts) {
  public static UserDetails of(User user, List<Post> posts) {
    return new UserDetails(
        user.id(),
        user.name(),
        user.username(),
        user.email(),
        user.address(),
        user.phone(),
        user.website(),
        user.company(),
        posts);
  }
}
