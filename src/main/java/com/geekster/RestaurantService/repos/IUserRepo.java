package com.geekster.RestaurantService.repos;

import com.geekster.RestaurantService.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User , Long> {
    User findFirstByEmail(String email);
}
