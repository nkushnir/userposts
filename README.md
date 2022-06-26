# User Posts Application
### Author: Nazar Kushnir (nazar.kushnir@gmail.com)

## About the application
* The Userposts application is using Spring Boot Web Flux to build non-blocking API to get user details and user posts information.
* The API uses [JSONPlaceholder](https://jsonplaceholder.typicode.com/) to retrieve user data.
* There are two endpoints available:
  * `GET /users/${id}` to get user details.
  * `GET /posts?userId=${userId}` to get list of user posts.

## Installation requirements
- JDK ver. 17 and above
- Maven ver. 3.8.1 and above


## Running Userposts application
1. From terminal move to Userposts application root folder
2. Execute the following command:
  ```
  $ mvn spring-boot:run
  ```
3. The application should be up and running on port 8080.

### cURL requests to test the Userposts application
1. ___Get user details information___
```
curl --location --request GET 'http://localhost:8080/users/1'
```
2. ___Get List of user posts___
```
curl --location --request GET 'http://localhost:8080/posts?userId=1'
```

## Testing Userposts application
1. From terminal move to Userposts application root folder
   2Execute the following command:
```
$ mvn test
```
