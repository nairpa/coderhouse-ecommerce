package edu.coderhouse.ecommerce.services;

import edu.coderhouse.ecommerce.exceptions.NotFoundException;
import edu.coderhouse.ecommerce.models.documents.*;
import edu.coderhouse.ecommerce.models.enums.StateEnum;
import edu.coderhouse.ecommerce.models.request.OrderRequest;
import edu.coderhouse.ecommerce.models.response.OrderResponse;
import edu.coderhouse.ecommerce.repository.CartRepository;
import edu.coderhouse.ecommerce.repository.OrderRepository;
import edu.coderhouse.ecommerce.repository.ProductRepository;
import edu.coderhouse.ecommerce.services.interfaces.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final SequenceGenerator sequenceGenerator;
    private final UserServiceImpl userService;
    public Optional<OrderResponse> getOrderById(Long orderId) {
        Optional<Order> order = orderRepository.findByOrderNumber(orderId);
        if(order.isPresent()) {
            log.info("La orden de fue encontrada existosamente" + LocalDate.now());
            return Optional.of(OrderResponse.builder()
                    .orderNumber(order.get().getOrderNumber())
                    .email(order.get().getEmail())
                    .items(order.get().getItems())
                    .total(order.get().getTotal())
                    .purchaseDate(order.get().getPurchaseDate())
                    .build());
        } else {
            log.error("La orden de compra no fue encontrada" + LocalDate.now());
            throw new NotFoundException("La orden de compra no fue encontrada");
        }
    }

    public void deleteOrder(Long orderId) {
        Optional<Order> order = orderRepository.findByOrderNumber(orderId);
        if(order.isPresent()) {
            log.info("La orden de compra fue encontrada" + LocalDate.now());
            orderRepository.deleteById(orderId);
        } else {
            log.error("La orden de compra no fue encontrada" + LocalDate.now());
            throw new NotFoundException("La orden de compra no fue encontrada");
        }
    }

    public OrderResponse createOrder(OrderRequest orderRequest) {
        Optional<Cart> cart = cartRepository.findByUserId(orderRequest.getUserId());
        Optional<User> user = userService.getUserById(orderRequest.getUserId());

        if(cart.isPresent() && user.isPresent()) {
            Order order = Order.builder()
                    .orderNumber(sequenceGenerator.generateSequence(Order.SEQUENCE_NAME))
                    .email(user.get().getEmail())
                    .userId(orderRequest.getUserId())
                    .state(StateEnum.GENERADA)
                    .items(cart.get().getItems())
                    .total(getTotalPrice(cart.get().getItems()))
                    .build();

            orderRepository.save(order);
            log.info("La orden de compra fue encontrada" + LocalDate.now());
            return OrderResponse.builder()
                    .orderNumber(order.getOrderNumber())
                    .email(order.getEmail())
                    .items(order.getItems())
                    .total(order.getTotal())
                    .build();
        } else {
            log.error("La orden de compra no fue encontrada" + LocalDate.now());
            throw new NotFoundException("No existe un carrito para ese usuario");
        }
    }

    public String purchaseOrder(Long orderId) {
        Optional<Order> order = orderRepository.findByOrderNumber(orderId);
        if(order.isPresent()) {
            if(order.get().getState() == StateEnum.VENDIDA) {
                return "Compra ya realizada";
            } else {
                order.get().setState(StateEnum.VENDIDA);
                order.get().setPurchaseDate(LocalDate.now());
                orderRepository.save(order.get());
                return "Compra realizada exitosamente";
            }
        } else {
            throw new NotFoundException("No existe orden con ese id");
        }
    }
    private Double getTotalPrice(List<CartItem> items) {
        Double total = 0.0;

        for(CartItem c : items) {
            Optional<Product> product = productRepository.findByCode(c.getCode());
            if (product.isPresent()) {
                total = total + (product.get().getPrecio() * c.getQuantity().doubleValue());
            }
        }

        return total;
    }
}
