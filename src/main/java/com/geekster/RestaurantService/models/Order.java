package com.geekster.RestaurantService.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FoodOrder_tb")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderID;
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;  //status will be created, dispatch , delivered

    @ManyToOne
    private User user;

    @OneToMany
    private List<Food> food;



}
