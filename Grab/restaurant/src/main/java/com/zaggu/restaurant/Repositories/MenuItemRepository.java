package com.zaggu.restaurant.Repositories;

import com.zaggu.restaurant.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
   // List<MenuItem> findByRestaurant_id(Long restaurant_id);
}
