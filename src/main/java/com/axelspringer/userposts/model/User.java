package com.axelspringer.userposts.model;

public record User(
    Long id,
    String name,
    String username,
    String email,
    Address address,
    String phone,
    String website,
    Company company) {
  public record Address(String street, String suite, String city, String zipcode, Geo geo) {

    public record Geo(Double lat, Double lng) {}
  }

  public record Company(String name, String catchPhrase, String bs) {}
}
