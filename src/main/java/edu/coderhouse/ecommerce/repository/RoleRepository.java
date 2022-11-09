package edu.coderhouse.ecommerce.repository;

import edu.coderhouse.ecommerce.models.Role;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByName(String name);
}
