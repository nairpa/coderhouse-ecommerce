package edu.coderhouse.ecommerce.repository;

import edu.coderhouse.ecommerce.models.documents.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, Long> {
    Optional<Order> findByOrderNumber(Long orderNumber);
}
