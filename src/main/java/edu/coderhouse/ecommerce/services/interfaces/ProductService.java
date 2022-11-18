package edu.coderhouse.ecommerce.services.interfaces;

import edu.coderhouse.ecommerce.models.documents.Product;
import edu.coderhouse.ecommerce.models.request.ProductRequest;
import edu.coderhouse.ecommerce.models.response.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    /**
     * Devuelve una lista con todos los productos cargados.
     * @return
     */
    List<Product> getProducts();

    /**
     * Devuelve un producto si es que existe
     * @param code codigo del producto
     * @return
     */
    Optional<ProductResponse> getProductById(Long code);

    /***
     * Devuelve una lista de productos según su categoría.
     * @param category
     * @return
     */
    List<ProductResponse> getProductsByCategory(String category);

    /***
     * Crea un nuevo producto y devuelve los datos del producto creado
     * @param productRequest
     * @return
     */
    ProductResponse createProduct(ProductRequest productRequest);

    /***
     * Actualiza un producto existente
     * @param productRequest
     * @param code
     * @return
     */
    ProductResponse updateProduct(ProductRequest productRequest, Long code);

    /***
     * Elimina un producto
     * @param code
     */
    void deleteProduct(Long code);
}
