package edu.coderhouse.ecommerce.repository;

import edu.coderhouse.ecommerce.models.documents.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    User findByUsername(String username);
    User findByEmail(String email);
}
