package com.zaggu.restaurant.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zaggu.restaurant.entities.MenuItem;
import com.zaggu.restaurant.entities.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
   //List<MenuItem> findByRestaurant_id(Long restaurant_id);
}