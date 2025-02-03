package com.zaggu.restaurant.Services;


import com.zaggu.restaurant.entities.MenuItem;
import com.zaggu.restaurant.entities.Restaurant;
import com.zaggu.restaurant.Repositories.MenuItemRepository;
import com.zaggu.restaurant.Repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    public Optional<MenuItem> getMenuItems(Long restaurant_id) {
         return menuItemRepository.findById(restaurant_id);
     }

     public Optional<Restaurant> getRestaurant(Long restaurant_id) {
        return restaurantRepository.findById(restaurant_id);
    }
}