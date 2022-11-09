package edu.coderhouse.ecommerce.services;

import edu.coderhouse.ecommerce.exceptions.NotFoundException;
import edu.coderhouse.ecommerce.models.Product;
import edu.coderhouse.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id)  {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            return product;
        } else {
            throw new NotFoundException("No existe producto con código " + id);
        }
    }
    public Product createProduct(Product product) {
        Product createdProduct = new Product();
        createdProduct.setDescripcion(product.getDescripcion());
        createdProduct.setCategoria(product.getCategoria());
        createdProduct.setPrecio(product.getPrecio());
        productRepository.save(createdProduct);
        return createdProduct;
    }

    public Product updateProduct(Product product, String id) {
        Optional<Product> updatedProduct = productRepository.findById(id);
        if(updatedProduct.isPresent()) {
            updatedProduct.get().setPrecio(product.getPrecio());
            updatedProduct.get().setDescripcion(product.getDescripcion());
            updatedProduct.get().setCategoria(product.getCategoria());

            productRepository.save(updatedProduct.get());
            return updatedProduct.get();
        } else {
            throw new NotFoundException("No existe producto con código " + id);
        }
    }

    public void deleteProduct(String id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new NotFoundException("No existe producto con código " + id);
        }
    }
}
