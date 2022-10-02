package edu.coderhouse.ecommerce.controllers;

import edu.coderhouse.ecommerce.models.User;
import edu.coderhouse.ecommerce.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping("api")
@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(
            value = "/users",
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping(
            value = "/users/{userId}",
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<User> getUserById(@PathVariable(name="userId") Long userId) {
        try {
            Optional<User> user = userService.getUserById(userId);
            return ResponseEntity.ok(user.get());
        } catch(Exception e) {
            if(e instanceof IllegalArgumentException) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.internalServerError().build();
            }
        }
    }
    @PostMapping(
            value = "/users",
            consumes = { MediaType.APPLICATION_JSON_VALUE},
            produces = { MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return ResponseEntity.created(URI.create("/users")).body(createdUser);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(
            value = "/users/{userId}",
            consumes = { MediaType.APPLICATION_JSON_VALUE},
            produces = { MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<User> updateUser(
            @PathVariable(name = "userId") Long userId,
            @RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(user, userId);
            return ResponseEntity.ok(updatedUser);
        } catch ( Exception e) {
            if(e instanceof IllegalArgumentException) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.internalServerError().build();
            }
        }
    }

    @DeleteMapping(
            value = "/users/{userId}",
            produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable(name = "userId") Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            if(e instanceof IllegalArgumentException) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.internalServerError().build();
            }
        }
    }
}
