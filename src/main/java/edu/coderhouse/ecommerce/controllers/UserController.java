package edu.coderhouse.ecommerce.controllers;

import edu.coderhouse.ecommerce.models.User;
import edu.coderhouse.ecommerce.models.request.LoginRequest;
import edu.coderhouse.ecommerce.models.request.RegisterRequest;
import edu.coderhouse.ecommerce.models.response.TokenResponse;
import edu.coderhouse.ecommerce.repository.UserRepository;
import edu.coderhouse.ecommerce.security.JwtUtils;
import edu.coderhouse.ecommerce.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.bson.types.ObjectId;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestMapping("api")
@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
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
    public ResponseEntity<?> getUserById(@PathVariable(name="userId") String userId) {
        Optional<User> user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }
    @PostMapping(
            value = "/auth/signup",
            consumes = { MediaType.APPLICATION_JSON_VALUE},
            produces = { MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest register) {
        if(userRepository.existsByUsername(register.getUsername())) {
            return ResponseEntity.badRequest().body("El usuario ya existe");
        };

        if(userRepository.existsByEmail(register.getEmail())) {
            return ResponseEntity.badRequest().body("Ya existe usuario con ese mail");
        }

        User user = new User();
        user.setUsername(register.getUsername());
        user.setName(register.getName());
        user.setEmail(register.getEmail());
        user.setPhoneNumber(register.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping(
            value = "/auth/signin",
            consumes = { MediaType.APPLICATION_JSON_VALUE},
            produces = { MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest login) throws UnsupportedEncodingException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(authentication);
        List<String> roles = user.getAuthorities()
                .stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(
                new TokenResponse(
                        jwt,
                        user.getId(),
                        user.getUsername(),
                        user.getEmail()
                )
        );
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
        User updatedUser = userService.updateUser(user, userId);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(
            value = "/users/{userId}",
            produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable(name = "userId") String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
