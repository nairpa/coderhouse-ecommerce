package edu.coderhouse.ecommerce.services;

import edu.coderhouse.ecommerce.exceptions.NotFoundException;
import edu.coderhouse.ecommerce.models.Role;
import edu.coderhouse.ecommerce.models.User;
import edu.coderhouse.ecommerce.repository.RoleRepository;
import edu.coderhouse.ecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.*;

@Service
@AllArgsConstructor
@Log4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

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
    public User createUser(final User user) {
        final User createdUser = new User();
        createdUser.setEmail(user.getEmail());
        createdUser.setName(user.getName());
        createdUser.setUsername(user.getUsername());
        createdUser.setPhoneNumber(user.getPhoneNumber());
        userRepository.save(createdUser);
        return createdUser;
    }

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