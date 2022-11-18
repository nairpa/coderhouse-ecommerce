package edu.coderhouse.ecommerce.controllers;

import edu.coderhouse.ecommerce.models.documents.Cart;
import edu.coderhouse.ecommerce.models.documents.CartItem;
import edu.coderhouse.ecommerce.models.request.CartItemRequest;
import edu.coderhouse.ecommerce.models.request.CartRequest;
import edu.coderhouse.ecommerce.models.response.CartResponse;
import edu.coderhouse.ecommerce.services.CartServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api")
@RestController
@AllArgsConstructor
public class CartController {
    private final CartServiceImpl cartServiceImpl;

    @GetMapping(value="/cart/{orderId}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Cart> getCart(@PathVariable(name="orderId") Long orderId) {
        Cart cart = cartServiceImpl.getCartByOrderNumber(orderId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping(value="/cart", produces={MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CartResponse> createCart(@RequestBody CartRequest cart) {
        CartResponse createCart = cartServiceImpl.createCart(cart);
        return ResponseEntity.ok(createCart);
    }

    @DeleteMapping(value="/cart", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteCart(Long orderNumber) {
        cartServiceImpl.deleteCart(orderNumber);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value="/cart/{orderId}/product")
    public ResponseEntity<List<CartItem>> getCartProducts(@PathVariable(name="orderId") Long orderId) {
        List<CartItem> products = cartServiceImpl.getCartProducts(orderId);
        return ResponseEntity.ok(products);
    }

    @PostMapping(value="/cart/{orderId}/product/{productId}")
    public ResponseEntity<?> addProductToCart(@PathVariable(name="orderId") Long orderId, @PathVariable(name="productId") Long productId, @RequestBody CartItemRequest product) {
        cartServiceImpl.addProductToCart(orderId, productId, product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value="/cart/{orderId}/product/{productId}")
    public ResponseEntity<?> deleteProductInCart(@PathVariable(name="orderId") Long orderId, @PathVariable(name="productId") Long productId) {
        cartServiceImpl.deleteProductOnCart(orderId, productId);
        return ResponseEntity.noContent().build();
    }
}
