package edu.coderhouse.ecommerce.services;

import edu.coderhouse.ecommerce.exceptions.NotFoundException;
import edu.coderhouse.ecommerce.models.User;
import edu.coderhouse.ecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> getUserById(final long id) {
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

    public User updateUser(final User user, final Long userId) {
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
    public void deleteUser(final Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            userRepository.deleteById(userId);
        } else {
            throw new NotFoundException("No existe usuario con ese Id " + userId);
        }
    }
}
