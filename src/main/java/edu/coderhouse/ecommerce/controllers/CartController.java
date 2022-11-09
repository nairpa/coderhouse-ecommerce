package edu.coderhouse.ecommerce.controllers;

import edu.coderhouse.ecommerce.models.Cart;
import edu.coderhouse.ecommerce.services.CartService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api")
@RestController
@AllArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping(value="/cart/{userId}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Cart>> getCart() {
        List<Cart> carts = this.cartService.getCart();
        return ResponseEntity.ok(carts);
    }

    @PostMapping(value="/cart", produces={MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createCart() {
        return ResponseEntity.ok("");
    }

    @PutMapping(value="/cart/{userId}", produces={MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Cart> updateCart(@PathVariable(name="id")String id, Cart cart) {
        Cart updateCart = this.cartService.updateCart(id, cart);
        return ResponseEntity.ok(updateCart);
    }

    @DeleteMapping(value="/cart", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteCart() {
        this.cartService.deleteCart();
        return ResponseEntity.noContent().build();
    }
}
