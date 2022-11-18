package edu.coderhouse.ecommerce.services.interfaces;

import edu.coderhouse.ecommerce.models.documents.Cart;
import edu.coderhouse.ecommerce.models.documents.CartItem;
import edu.coderhouse.ecommerce.models.request.CartItemRequest;
import edu.coderhouse.ecommerce.models.request.CartRequest;
import edu.coderhouse.ecommerce.models.response.CartResponse;

import java.util.List;
import java.util.Optional;

public interface CartService {
    /***
     * Busca todos los carritos existentes.
     * @return devuelve una lista de carritos.
     */
    public List<Cart> getCarts();

    /***
     * Crea un nuevo carrito
     * @param cart los datos del carrito a crear.
     * @return devuelve los datos del carrito creado.
     */
    public CartResponse createCart(CartRequest cart);

    /***
     * Elimina un carrito según su id.
     * @param orderNumber
     */
    public void deleteCart(Long orderNumber);

    /***
     * Busca los productos de un carrito según su id.
     * @param orderNumber
     * @return una lista de productos.
     */
    public List<CartItem> getCartProducts(Long orderNumber);

    /**
     * Agrega un nuevo producto al carrito.
     * @param orderNumber
     * @param item
     */
    public void addProductToCart(Long orderNumber, Long productId, CartItemRequest item);

    /***
     * Elimina un producto en el carrito.
     * @param orderNumber
     * @param code
     */
    public void deleteProductOnCart(Long orderNumber, Long code);

    /***
     * Verifica que el producto no exista en el carrito
     * @param code
     * @param cart
     * @return si existe devuelve false, sino devuelve true
     */
    private boolean checkProductNotExistsOnCart(Long code, Cart cart) {
        return false;
    };

    /***
     * Busca el producto en el carrito según el codigo.
     * @param code
     * @param items
     * @return devuelve el producto si lo encuentra.
     */
    private Optional<CartItem> getCartItemByProductCode(Long code, List<CartItem> items) {
        return Optional.empty();
    };
}
