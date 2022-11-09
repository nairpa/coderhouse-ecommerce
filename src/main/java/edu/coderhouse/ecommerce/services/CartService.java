package edu.coderhouse.ecommerce.services;

import edu.coderhouse.ecommerce.exceptions.NotFoundException;
import edu.coderhouse.ecommerce.models.Cart;
import edu.coderhouse.ecommerce.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public List<Cart> getCart() {
        return this.cartRepository.findAll();
    }

    public Cart updateCart(String id, Cart cart) {
        Optional<Cart> updateCart = this.cartRepository.findById(id);
        if(updateCart.isPresent()) {
            updateCart.get().setCantidad(cart.getCantidad());
            this.cartRepository.save(updateCart.get());

            return updateCart.get();
        } else {
            throw new NotFoundException("No existe un producto en el carrito para ese id" + id);
        }
    }

    public void deleteCart() {
        this.cartRepository.deleteAll();
    }
}
