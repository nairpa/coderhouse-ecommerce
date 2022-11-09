package edu.coderhouse.ecommerce.services;

import edu.coderhouse.ecommerce.exceptions.NotFoundException;
import edu.coderhouse.ecommerce.models.Role;
import edu.coderhouse.ecommerce.models.User;
import edu.coderhouse.ecommerce.repository.RoleRepository;
import edu.coderhouse.ecommerce.repository.UserRepository;
import edu.coderhouse.ecommerce.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    //private final AuthenticationManager authenticationManager;
    //private final JwtUtils jwtUtils;
    //private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            log.error("Usuario no encontrado en la base de datos");
            throw new UsernameNotFoundException("Usuario no encontrado en la base de datos");
        } else {
            log.info("Usuario encontrado");
        }

        return User.build(user);
    }

    public Optional<User> getUserById(final String id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return user;
        } else {
            throw new NotFoundException("No existe usuario con Id " + id);
        }
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
    /*public Optional<User> signupUser(final RegisterRequest register) {
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
    }*/
    public User updateUser(final User user, final String userId) {
        Optional<User> updatedUser = userRepository.findById(userId);

        if(updatedUser.isPresent()) {
            updatedUser.get().setName(user.getName());
            updatedUser.get().setEmail(user.getEmail());
            updatedUser.get().setUsername(user.getUsername());
            updatedUser.get().setPhoneNumber(user.getPhoneNumber());

            userRepository.save(updatedUser.get());
            return updatedUser.get();
        } else {
            throw new NotFoundException("No existe usuario con Id " + userId);
        }
    }

    public void deleteUser(final String userId) {
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            userRepository.deleteById(userId);
        } else {
            throw new NotFoundException("No existe usuario con ese Id " + userId);
        }
    }

    public Role saveRole(Role role) {
        log.info("Guardando nuevo role en la database");
        return roleRepository.save(role);
    };

    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    };
}