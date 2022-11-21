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
import java.util.Optional;

@RequestMapping("api")
@RestController
@AllArgsConstructor
public class CartController {
    private final CartServiceImpl cartServiceImpl;

    @GetMapping(value="/cart/{userId}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CartResponse> getCart(@PathVariable(name="userId") String userId) {
        CartResponse cart = cartServiceImpl.getCartByUser(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping(value="/cart", produces={MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CartResponse> createCart(@RequestBody CartRequest cart) {
        CartResponse createCart = cartServiceImpl.createCart(cart);
        return ResponseEntity.ok(createCart);
    }

    @DeleteMapping(value="/cart/{userId}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteCart(String userId) {
        cartServiceImpl.deleteCart(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value="/cart/{userId}/product")
    public ResponseEntity<List<CartItem>> getCartProducts(@PathVariable(name="userId") String userId) {
        List<CartItem> products = cartServiceImpl.getCartProducts(userId);
        return ResponseEntity.ok(products);
    }

    @GetMapping(value="/cart/{userId}/product/{productId}")
    public ResponseEntity<?> getCartProducts(@PathVariable(name="userId") String userId, @PathVariable(name="productId") Long productId) {
        Optional<CartItem> product = cartServiceImpl.getCartProductById(userId, productId);
        return ResponseEntity.ok(product);
    }

    @PostMapping(value="/cart/{userId}/product/{productId}")
    public ResponseEntity<?> addProductToCart(@PathVariable(name="userId") String userId, @PathVariable(name="productId") Long productId, @RequestBody CartItemRequest product) {
        cartServiceImpl.addProductToCart(userId, productId, product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value="/cart/{userId}/product/{productId}")
    public ResponseEntity<?> deleteProductInCart(@PathVariable(name="userId") String cartId, @PathVariable(name="productId") Long productId) {
        cartServiceImpl.deleteProductOnCart(cartId, productId);
        return ResponseEntity.noContent().build();
    }
}
