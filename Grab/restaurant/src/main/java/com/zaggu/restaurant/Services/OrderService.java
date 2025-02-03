package com.zaggu.restaurant.Services;



import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.zaggu.restaurant.Repositories.*;
import com.zaggu.restaurant.entities.*;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;  // ✅ Correct repository
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;

    @Transactional
    public Order placeOrder(Long customerId, Long restaurant_id, List<Long> menuItemIds) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Restaurant restaurant = restaurantRepository.findById(restaurant_id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        List<MenuItem> menuItems = menuItemRepository.findAllById(menuItemIds);
        if (menuItems.isEmpty()) {
            throw new RuntimeException("No menu items found");
        }
       
            

        BigDecimal totalPrice = menuItems.stream()
                .map(MenuItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
         // ✅ Convert menu items to a comma-separated string
        String orderedItems = menuItems.stream()
                .map(MenuItem::getName) // You can use getId() instead if needed
                .collect(Collectors.joining(", "));
        // ✅ Create Order
        Order order = Order.builder()
                .customer(customer)
                .restaurant(restaurant)   
                .status(OrderStatus.PLACED)
                .orderedItems(orderedItems) 
                .totalPrice(totalPrice)
                .build();

        // ✅ Ensure this is `orderRepository.save(order);`
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}

