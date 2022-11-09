package edu.coderhouse.ecommerce.services;

import edu.coderhouse.ecommerce.models.User;
import edu.coderhouse.ecommerce.models.request.LoginRequest;
import edu.coderhouse.ecommerce.models.request.RegisterRequest;
import edu.coderhouse.ecommerce.models.response.TokenResponse;
import edu.coderhouse.ecommerce.repository.UserRepository;
import edu.coderhouse.ecommerce.security.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    public Optional<User> signupUser(final RegisterRequest register) {
        if(userRepository.existsByUsername(register.getUsername())) {
            return Optional.empty();
        };

        if(userRepository.existsByEmail(register.getEmail())) {
            return Optional.empty();
        }

        User user = new User();
        user.setUsername(register.getUsername());
        user.setName(register.getName());
        user.setEmail(register.getEmail());
        user.setPhoneNumber(register.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        userRepository.save(user);
        return Optional.of(user);
    }

    public Optional<TokenResponse> signinUser(final LoginRequest login) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        try {
            String jwt = jwtUtils.generateJwtToken(authentication);
            List<String> roles = user.getAuthorities()
                    .stream()
                    .map(role -> role.getAuthority())
                    .collect(Collectors.toList());
            return Optional.of(new TokenResponse(
                    jwt,
                    user.getId(),
                    user.getUsername(),
                    user.getEmail()
            ));
        } catch(UnsupportedEncodingException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        }

        return Optional.empty();
    }
}
