package com.geekster.RestaurantService.repos;

import com.geekster.RestaurantService.models.Order;
import com.geekster.RestaurantService.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepo extends JpaRepository<Order , Long> {
    List<Order> findByUser(User user);
}
