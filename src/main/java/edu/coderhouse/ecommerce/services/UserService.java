package edu.coderhouse.ecommerce.services;

import edu.coderhouse.ecommerce.models.User;

import java.util.List;

public class UserService {
    public List<User> listUsers() {
        return List.of(
                new User(
                        "npalacios",
                        "Nair",
                        "(341)6019924",
                        "1234",
                        "npalacios@live.com"
                )
        );
    }
}
