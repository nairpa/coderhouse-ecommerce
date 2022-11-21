package edu.coderhouse.ecommerce.services;

import edu.coderhouse.ecommerce.exceptions.AuthErrorException;
import edu.coderhouse.ecommerce.exceptions.UserExistsException;
import edu.coderhouse.ecommerce.models.documents.User;
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
import java.time.LocalDate;
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
            log.error("El usuario ya existe" + LocalDate.now());
            throw new UserExistsException("El usuario ya existe");
        };

        if(userRepository.existsByEmail(register.getEmail())) {
            log.error("El usuario ya existe" + LocalDate.now());
            throw new UserExistsException("El usuario ya existe");
        }

        User user = new User();
        user.setUsername(register.getUsername());
        user.setName(register.getName());
        user.setEmail(register.getEmail());
        user.setPhoneNumber(register.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        userRepository.save(user);
        log.info("Usuario creado existosamente" + LocalDate.now());
        return Optional.of(user);
    }

    public Optional<TokenResponse> signinUser(final LoginRequest login) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        try {
            String jwt = jwtUtils.generateJwtToken(authentication);

            log.info("Usuario autenticado existosamente" + LocalDate.now());
            return Optional.of(new TokenResponse(
                    jwt,
                    user.getId(),
                    user.getUsername(),
                    user.getEmail()
            ));
        } catch(UnsupportedEncodingException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
            throw new AuthErrorException("Usuario no autorizado");
        }
    }
}
