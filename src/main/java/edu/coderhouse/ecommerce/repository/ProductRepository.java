package edu.coderhouse.ecommerce.repository;

import edu.coderhouse.ecommerce.models.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, ObjectId> {
}
