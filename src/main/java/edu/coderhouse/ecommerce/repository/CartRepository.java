package edu.coderhouse.ecommerce.repository;

import edu.coderhouse.ecommerce.models.Cart;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, ObjectId> {
}
