package edu.coderhouse.ecommerce.controllers;

import edu.coderhouse.ecommerce.models.User;
import edu.coderhouse.ecommerce.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api")
@RestController
public class UserController {
    private UserService userService = new UserService();

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.listUsers();
    }
}
