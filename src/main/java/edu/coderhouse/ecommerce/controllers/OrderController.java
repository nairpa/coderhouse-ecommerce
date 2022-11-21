package edu.coderhouse.ecommerce.controllers;

import edu.coderhouse.ecommerce.models.request.OrderRequest;
import edu.coderhouse.ecommerce.models.response.OrderResponse;
import edu.coderhouse.ecommerce.repository.OrderRepository;
import edu.coderhouse.ecommerce.services.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {
    private final OrderServiceImpl orderService;

    @GetMapping(value="/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable(name="orderId") Long orderId) {
        Optional<OrderResponse> order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    @PostMapping(value="/")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest order) {
        OrderResponse createOrder = orderService.createOrder(order);
        return ResponseEntity.created(URI.create(("/order"))).body(createOrder);
    }

    @PostMapping(value="/{orderId}")
    public ResponseEntity<?> purchaseOrder(@PathVariable(name="orderId") Long orderId) {
        String message = orderService.purchaseOrder(orderId);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping(value="/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable(name="orderId") Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}

