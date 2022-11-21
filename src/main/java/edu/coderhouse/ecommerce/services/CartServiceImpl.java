package edu.coderhouse.ecommerce.services;

import edu.coderhouse.ecommerce.exceptions.NotFoundException;
import edu.coderhouse.ecommerce.models.documents.*;
import edu.coderhouse.ecommerce.models.enums.StateEnum;
import edu.coderhouse.ecommerce.models.request.CartItemRequest;
import edu.coderhouse.ecommerce.models.request.CartRequest;
import edu.coderhouse.ecommerce.models.response.CartResponse;
import edu.coderhouse.ecommerce.repository.CartRepository;
import edu.coderhouse.ecommerce.repository.ProductRepository;
import edu.coderhouse.ecommerce.services.interfaces.CartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }

    public CartResponse createCart(CartRequest cart) {
        Cart createCart = Cart.builder()
                .date(LocalDate.now())
                .address(cart.getAddress())
                .items(new ArrayList<>())
                .userId(cart.getUserId())
                .build();

        cartRepository.save(createCart);
        CartResponse cartResponse = CartResponse.builder()
                .date(createCart.getDate())
                .items(createCart.getItems())
                .address(createCart.getAddress())
                .userId(createCart.getUserId())
                .build();
        log.info("Carrito creado existosamente" + LocalDate.now());
        return cartResponse;
    }
    public void deleteCart(String userId) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        if(cart.isPresent()) {
            cartRepository.deleteByUserId(userId);
            log.info("Carrito eliminado existosamente" + LocalDate.now());
        } else {
            log.error("No existe carrito para usuario" + userId + LocalDate.now());
            throw new NotFoundException("No existe carrito con para el usuario de id" + userId);
        }
    }

    public CartResponse getCartByUser(String userId) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);

        if(cart.isPresent()) {
            log.info("Carrito encontrado existosamente" + LocalDate.now());
            return CartResponse.builder()
                    .items(cart.get().getItems())
                    .date(cart.get().getDate())
                    .address(cart.get().getAddress())
                    .build();
        } else {
            log.error("No existe carrito para usuario" + userId + LocalDate.now());
            throw new NotFoundException("No existe carrito para el usuario de id " + userId);
        }
    }

    public List<CartItem> getCartProducts(String userId) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        if(cart.isPresent()) {
            log.info("Productos del carrito encontrados exitosamente" + LocalDate.now());
            return cart.get().getItems();
        } else {
            log.error("No existe carrito para usuario" + userId + LocalDate.now());
            throw new NotFoundException("No existe carrito para el usuario de id " + userId);
        }
    }

    public Optional<CartItem> getCartProductById(String userId, Long productId) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        Optional<CartItem> cartItem = Optional.empty();

        if(cart.isPresent()) {
            for(CartItem c : cart.get().getItems()) {
                if(c.getCode().equals(productId)) {
                    cartItem = Optional.of((c));
                }
            }
            log.info("Producto en el carrito encontrado exitosamente" + LocalDate.now());
            return cartItem;
        } else {
            log.error("No existe carrito para usuario" + userId + LocalDate.now());
            throw new NotFoundException("No existe carrito para el usuario de id " + userId);
        }
    }

    public void addProductToCart(String userId, Long productId, CartItemRequest item) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        Optional<Product> product = productRepository.findByCode(productId);
        if(cart.isPresent() && product.isPresent()) {
            if(checkProductNotExistsOnCart(product.get().getCode(), cart.get())) {
                CartItem cartItem = CartItem.builder()
                                        .quantity(item.getQuantity())
                                        .code(product.get().getCode())
                                        .build();

                cart.get().getItems().add(cartItem);
                log.info("Producto agregado correctamente al carrito" + LocalDate.now());
                cartRepository.save(cart.get());
            } else {
                Optional<CartItem> cartItem = getCartItemByProductCode(product.get().getCode(), cart.get().getItems());
                if(cartItem.isPresent()) {
                    cartItem.get().setQuantity(cartItem.get().getQuantity() + item.getQuantity());
                    cartRepository.save(cart.get());
                    log.info("Producto actualizado correctamente" + LocalDate.now());
                }
            };
        } else if(cart.isEmpty()) {
            log.error("No existe carrito para usuario" + userId + LocalDate.now());
            throw new NotFoundException("No existe carrito para el usuario de id " + userId);
        } else if(product.isEmpty()) {
            log.error("No existe producto para ese codigo" + productId + LocalDate.now());
            throw new NotFoundException("No existe producto con ese codigo ");
        }
    }

    public void deleteProductOnCart(String userId, Long code) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        Optional<Product> product = productRepository.findByCode(code);

        if(cart.isPresent() && product.isPresent()) {
            Optional<CartItem> cartItem = getCartItemByProductCode(code, cart.get().getItems());
            if (cartItem.isPresent()) {
                List<CartItem> items = cart.get()
                        .getItems()
                        .stream()
                        .filter(item -> !item.getCode().equals(cartItem.get().getCode()))
                        .collect(Collectors.toList());
                cart.get().setItems(items);
                log.info("Producto eliminado correctamente del carrito" + LocalDate.now());
                cartRepository.save(cart.get());
            }
        }
    }

    private boolean checkProductNotExistsOnCart(Long code, Cart cart) {
        boolean exists = true;

        for(CartItem c: cart.getItems()) {
            if (c.getCode().equals(code)) {
                exists = false;
            }
        }

        return exists;
    }

    private Optional<CartItem> getCartItemByProductCode(Long code, List<CartItem> items) {
        Optional<CartItem> exists = Optional.empty();

        for(CartItem c : items) {
            if(c.getCode().equals(code)) {
                exists = Optional.of(c);
            }
        }

        return exists;
    }
}
