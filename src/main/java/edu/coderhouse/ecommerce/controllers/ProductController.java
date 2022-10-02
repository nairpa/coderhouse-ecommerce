package edu.coderhouse.ecommerce.controllers;

import edu.coderhouse.ecommerce.models.Product;
import edu.coderhouse.ecommerce.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(value="/productos", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping(value="/productos/{codigo}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> getProductById(@PathVariable(name="codigo") Long codigo) {
        try {
            Optional<Product> product = productService.getProductById(codigo);
            return ResponseEntity.ok(product.get());
        } catch(Exception e) {
            if(e instanceof IllegalArgumentException) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.internalServerError().build();
            }
        }
    }

    @PostMapping(
            value="/productos",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            Product producto = productService.createProduct(product);
            return ResponseEntity.created(URI.create("/productos")).body(producto);
        } catch(Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(
            value="/productos/{codigo}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable(name="codigo") Long codigo) {
        try {
            Product updatedProduct = productService.updateProduct(product, codigo);
            return ResponseEntity.ok(updatedProduct);
        } catch ( Exception e) {
            if(e instanceof IllegalArgumentException) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.internalServerError().build();
            }
        }
    }

    @DeleteMapping(
            value="/productos/{codigo}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> deleteProduct(@PathVariable(name="codigo") Long codigo) {
        try {
            productService.deleteProduct(codigo);
            return ResponseEntity.noContent().build();
        } catch(Exception e) {
            if(e instanceof IllegalArgumentException) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.internalServerError().build();
            }
        }
    }
}
