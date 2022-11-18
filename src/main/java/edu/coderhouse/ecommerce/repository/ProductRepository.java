package edu.coderhouse.ecommerce.repository;

import edu.coderhouse.ecommerce.models.documents.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findByCode(Long code);
    List<Product> findByCategory(String category);
    void deleteByCode(Long code);
}
