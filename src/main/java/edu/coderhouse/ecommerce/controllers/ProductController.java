package edu.coderhouse.ecommerce.controllers;

import edu.coderhouse.ecommerce.models.documents.Product;
import edu.coderhouse.ecommerce.models.request.ProductRequest;
import edu.coderhouse.ecommerce.models.response.ProductResponse;
import edu.coderhouse.ecommerce.services.ProductServiceImpl;
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
    private final ProductServiceImpl productServiceImpl;

    @GetMapping(value="/product", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productServiceImpl.getProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping(value="/product/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getProductById(@PathVariable(name="id") Long code) {
        Optional<ProductResponse> product = productServiceImpl.getProductById(code);
        return ResponseEntity.ok(product);
    }

    @GetMapping(value="/product")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@RequestParam(name="category", defaultValue = "") String category) {
        List<ProductResponse> products = productServiceImpl.getProductsByCategory(category);
        return ResponseEntity.ok(products);
    }
    @PostMapping(
            value="/product",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest product) {
        ProductResponse producto = productServiceImpl.createProduct(product);
        return ResponseEntity.created(URI.create("/productos")).body(producto);
    }

    @PutMapping(
            value="/product/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductResponse> updateProduct(@Valid @RequestBody ProductRequest product, @PathVariable(name="id") Long code) {
            ProductResponse updatedProduct = productServiceImpl.updateProduct(product, code);
            return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping(
            value="/product/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> deleteProduct(@PathVariable(name="id") Long code) {
        productServiceImpl.deleteProduct(code);
        return ResponseEntity.noContent().build();
    }
}
