package com.zaggu.restaurant.controller;




import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zaggu.restaurant.Services.OrderService;
import com.zaggu.restaurant.entities.Order;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) {
        try {
            // Validate request parameters
            if (request.getCustomerId() == null) {
                throw new IllegalArgumentException("Customer ID must not be null");
            }
            if (request.getRestaurant_id() == null) {
                throw new IllegalArgumentException("Restaurant ID must not be null");
            }
            if (request.getMenuItemIds() == null || request.getMenuItemIds().isEmpty()) {
                throw new IllegalArgumentException("At least one menu item must be selected");
            }

            // Call the service layer
            Order order = orderService.placeOrder(
                    request.getCustomerId(),
                    request.getRestaurant_id(),
                    request.getMenuItemIds()
            );

            return ResponseEntity.ok(order);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while placing the order");
        }
    }
    @PostMapping("/place")

    
    public ResponseEntity<Order> placeOrder(
            @RequestParam Long customerId,
            @RequestParam Long restaurant_id,
            @RequestParam List<Long> menuItemIds) {
        
        Order order = orderService.placeOrder(customerId, restaurant_id, menuItemIds);
        return ResponseEntity.ok(order);
        
    }

    // ✅ Accept JSON request body instead of URL parameters
    @PostMapping("/place/json")
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest orderRequest) {
        Order order = orderService.placeOrder(
                orderRequest.getCustomerId(),
                orderRequest.getRestaurant_id(),
                orderRequest.getMenuItemIds()

                
        );
        return ResponseEntity.ok(order);
    }
    

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
