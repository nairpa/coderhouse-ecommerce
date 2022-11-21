package edu.coderhouse.ecommerce.services;

import edu.coderhouse.ecommerce.exceptions.NotFoundException;
import edu.coderhouse.ecommerce.models.documents.Product;
import edu.coderhouse.ecommerce.models.request.ProductRequest;
import edu.coderhouse.ecommerce.models.response.ProductResponse;
import edu.coderhouse.ecommerce.repository.ProductRepository;
import edu.coderhouse.ecommerce.services.interfaces.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final SequenceGeneratorImpl sequenceGenerator;
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Optional<ProductResponse> getProductById(Long code)  {
        Optional<Product> product = productRepository.findByCode(code);
        if(product.isPresent()) {
            log.info("El producto fue encontrado exitosamente" + LocalDate.now());
            return Optional.of(ProductResponse.builder()
                    .precio(product.get().getPrecio())
                    .descripcion(product.get().getDescripcion())
                    .category(product.get().getCategory())
                    .code(product.get().getCode())
                    .build());
        } else {
            log.error("No existe producto con codigo" + code + LocalDate.now());
            throw new NotFoundException("No existe producto con código " + code);
        }
    }

    public List<ProductResponse> getProductsByCategory(String category) {
        List<Product> products = productRepository.findByCategory(category);
        log.info("Los productos fueron encontrados exitosamente" + LocalDate.now());
        return products
                .stream()
                .map(product ->
                        ProductResponse
                                .builder()
                                .code(product.getCode())
                                .category(product.getCategory())
                                .descripcion(product.getDescripcion())
                                .precio(product.getPrecio())
                                .build()
                )
                .collect(Collectors.toList());
    }
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .code(sequenceGenerator.generateSequence(Product.SEQUENCE_NAME))
                .category(productRequest.getCategory())
                .descripcion(productRequest.getDescripcion())
                .precio(productRequest.getPrecio())
                .build();
        productRepository.save(product);
        log.info("El producto creado exitosamente" + LocalDate.now());
        ProductResponse productResponse = ProductResponse.builder()
                .code(product.getCode())
                .precio(product.getPrecio())
                .descripcion(product.getDescripcion())
                .category(product.getCategory())
                .build();
        return productResponse;
    }

    public ProductResponse updateProduct(ProductRequest product, Long code) {
        Optional<Product> updatedProduct = productRepository.findByCode(code);
        if(updatedProduct.isPresent()) {
            updatedProduct.get().setPrecio(product.getPrecio());
            updatedProduct.get().setDescripcion(product.getDescripcion());
            updatedProduct.get().setCategory(product.getCategory());

            productRepository.save(updatedProduct.get());
            log.info("El producto fue actualizado exitosamente" + LocalDate.now());
            ProductResponse productResponse = ProductResponse.builder()
                    .precio(product.getPrecio())
                    .descripcion(product.getDescripcion())
                    .code(updatedProduct.get().getCode())
                    .category(product.getCategory())
                    .build();
            return productResponse;
        } else {
            log.error("El producto no fue encontrado" + LocalDate.now());
            throw new NotFoundException("No existe producto con código " + code);
        }
    }

    public void deleteProduct(Long code) {
        Optional<Product> product = productRepository.findByCode(code);
        if(product.isPresent()) {
            log.info("El producto fue encontrado exitosamente" + LocalDate.now());
            productRepository.deleteById(product.get().getId());
        } else {
            log.error("El producto no fue encontrado" + LocalDate.now());
            throw new NotFoundException("No existe producto con código " + code);
        }
    }
}
