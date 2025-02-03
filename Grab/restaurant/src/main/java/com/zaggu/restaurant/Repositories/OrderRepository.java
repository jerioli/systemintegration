package com.zaggu.restaurant.Repositories;

import com.zaggu.restaurant.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // ✅ Required to be recognized as a Spring Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
