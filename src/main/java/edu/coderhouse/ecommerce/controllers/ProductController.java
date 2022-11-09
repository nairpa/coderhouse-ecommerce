package edu.coderhouse.ecommerce.controllers;

import edu.coderhouse.ecommerce.models.Product;
import edu.coderhouse.ecommerce.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(value="/product", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping(value="/product/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getProductById(@PathVariable(name="id") String id) {
        Optional<Product> product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping(
            value="/product",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product producto = productService.createProduct(product);
        return ResponseEntity.created(URI.create("/productos")).body(producto);
    }

    @PutMapping(
            value="/product/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product, @PathVariable(name="id") String id) {
            Product updatedProduct = productService.updateProduct(product, id);
            return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping(
            value="/product/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> deleteProduct(@PathVariable(name="id") String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
