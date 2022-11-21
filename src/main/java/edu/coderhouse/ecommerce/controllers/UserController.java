package edu.coderhouse.ecommerce.controllers;

import edu.coderhouse.ecommerce.models.documents.User;
import edu.coderhouse.ecommerce.repository.UserRepository;
import edu.coderhouse.ecommerce.security.JwtUtils;
import edu.coderhouse.ecommerce.services.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("api")
@RestController
@AllArgsConstructor
public class UserController {
    private final UserServiceImpl userServiceImpl;
    
    @GetMapping(
            value = "/users",
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userServiceImpl.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping(
            value = "/users/{userId}",
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<?> getUserById(@PathVariable(name="userId") String userId) {
        Optional<User> user = userServiceImpl.getUserById(userId);
        return ResponseEntity.ok(user);
    }
    @PutMapping(
            value = "/users/{userId}",
            consumes = { MediaType.APPLICATION_JSON_VALUE},
            produces = { MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<User> updateUser(
            @Valid
            @PathVariable(name = "userId") String userId,
            @RequestBody User user) {
        User updatedUser = userServiceImpl.updateUser(user, userId);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(
            value = "/users/{userId}",
            produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable(name = "userId") String userId) {
        userServiceImpl.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
