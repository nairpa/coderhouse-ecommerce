package edu.coderhouse.ecommerce.repository;

import edu.coderhouse.ecommerce.models.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
}
