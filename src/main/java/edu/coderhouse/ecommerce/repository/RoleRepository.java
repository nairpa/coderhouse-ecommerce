package edu.coderhouse.ecommerce.repository;

import edu.coderhouse.ecommerce.models.documents.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByName(String name);
}
