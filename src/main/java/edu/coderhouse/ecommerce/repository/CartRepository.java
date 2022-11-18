package edu.coderhouse.ecommerce.repository;

import edu.coderhouse.ecommerce.models.documents.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CartRepository extends MongoRepository<Cart, String> {
    Optional<Cart> findCartByOrderNumber(Long orderId);
    void deleteByOrderNumber(Long orderId);
}
