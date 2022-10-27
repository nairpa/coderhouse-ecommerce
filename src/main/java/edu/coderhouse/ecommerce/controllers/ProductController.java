package edu.coderhouse.ecommerce.controllers;

import edu.coderhouse.ecommerce.models.Product;
import edu.coderhouse.ecommerce.services.ProductService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

    @GetMapping(value="/productos", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping(value="/productos/{codigo}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getProductById(@PathVariable(name="codigo") ObjectId codigo) {
        Optional<Product> product = productService.getProductById(codigo);
        return ResponseEntity.ok(product);
    }

    @PostMapping(
            value="/productos",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product producto = productService.createProduct(product);
        return ResponseEntity.created(URI.create("/productos")).body(producto);
    }

    @PutMapping(
            value="/productos/{codigo}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product, @PathVariable(name="codigo") ObjectId codigo) {
            Product updatedProduct = productService.updateProduct(product, codigo);
            return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping(
            value="/productos/{codigo}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> deleteProduct(@PathVariable(name="codigo") ObjectId codigo) {
        productService.deleteProduct(codigo);
        return ResponseEntity.noContent().build();
    }
}
