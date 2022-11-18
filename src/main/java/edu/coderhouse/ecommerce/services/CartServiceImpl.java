package edu.coderhouse.ecommerce.services;

import edu.coderhouse.ecommerce.exceptions.NotFoundException;
import edu.coderhouse.ecommerce.models.documents.Cart;
import edu.coderhouse.ecommerce.models.documents.CartItem;
import edu.coderhouse.ecommerce.models.documents.Product;
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
    private final SequenceGeneratorImpl sequenceGenerator;

    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }

    public CartResponse createCart(CartRequest cart) {
        Cart createCart = Cart.builder()
                .date(LocalDate.now())
                .orderNumber(sequenceGenerator.generateSequence(Cart.SEQUENCE_NAME))
                .address(cart.getAddress())
                .items(new ArrayList<>())
                .build();

        cartRepository.save(createCart);
        CartResponse cartResponse = CartResponse.builder()
                .date(createCart.getDate())
                .items(createCart.getItems())
                .address(createCart.getAddress())
                .orderNumber(createCart.getOrderNumber())
                .build();

        return cartResponse;
    }

    public void deleteCart(Long cartId) {
        Optional<Cart> cart = cartRepository.findCartByOrderNumber(cartId);
        if(cart.isPresent()) {
            cartRepository.deleteByOrderNumber(cartId);
        } else {
            throw new NotFoundException("No existe carrito con id" + cartId);
        }
    }

    public Cart getCartByOrderNumber(Long orderId) {
        Optional<Cart> cart = cartRepository.findCartByOrderNumber(orderId);
        if(cart.isPresent()) {
            return cart.get();
        } else {
            throw new NotFoundException("No existe carrito con orden de id " + orderId);
        }
    }

    public List<CartItem> getCartProducts(Long orderId) {
        Optional<Cart> cart = cartRepository.findCartByOrderNumber(orderId);
        if(cart.isPresent()) {
            return cart.get().getItems();
        } else {
            throw new NotFoundException("No existe carrito con id " + orderId);
        }
    }

    public void addProductToCart(Long orderId, Long productId, CartItemRequest item) {
        Optional<Cart> cart = cartRepository.findCartByOrderNumber(orderId);
        Optional<Product> product = productRepository.findByCode(productId);
        if(cart.isPresent() && product.isPresent()) {
            if(checkProductNotExistsOnCart(product.get().getCode(), cart.get())) {
                CartItem cartItem = CartItem.builder()
                                        .quantity(item.getQuantity())
                                        .code(product.get().getCode())
                                        .build();

                cart.get().getItems().add(cartItem);
                log.info(item.getQuantity().toString());
                cartRepository.save(cart.get());
            } else {
                Optional<CartItem> cartItem = getCartItemByProductCode(product.get().getCode(), cart.get().getItems());
                if(cartItem.isPresent()) {
                    cartItem.get().setQuantity(cartItem.get().getQuantity() + item.getQuantity());
                    cartRepository.save(cart.get());
                }
            };
        } else if(cart.isEmpty()) {
            throw new NotFoundException("No existe carrito con orden de id " + orderId);
        } else if(product.isEmpty()) {
            throw new NotFoundException("No existe producto con ese codigo ");
        }
    }

    public void deleteProductOnCart(Long orderId, Long code) {
        Optional<Cart> cart = cartRepository.findCartByOrderNumber(orderId);
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
