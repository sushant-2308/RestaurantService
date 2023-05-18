package com.geekster.RestaurantService.services;

import com.geekster.RestaurantService.models.AuthenticationToken;
import com.geekster.RestaurantService.models.Order;
import com.geekster.RestaurantService.models.OrderStatus;
import com.geekster.RestaurantService.repos.IOrderRepo;
import com.geekster.RestaurantService.repos.ITokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    IOrderRepo orderRepo;

    @Autowired
    ITokenRepo tokenRepo;

    public void placeOrder(Order order, String token) {
        AuthenticationToken token1 = tokenRepo.findFirstByToken(token);
        order.setStatus(OrderStatus.valueOf("CREATED"));
        order.setUser(token1.getUser());
        orderRepo.save(order);
    }

    public List<Order> getOrders(String email, String token) {
        AuthenticationToken token1 = tokenRepo.findFirstByToken(token);
        if(token1.getUser().getRole().getRoleId()==1){
            return orderRepo.findAll();
        }else{
            List<Order> orderList = new ArrayList<>();
            orderList = orderRepo.findByUser(token1.getUser());
            return orderList;
        }
    }

    public void updateOrder(String email, String token , Order order) {
        AuthenticationToken token1 = tokenRepo.findFirstByToken(token);
        if (order.getUser() == token1.getUser() || token1.getUser().getRole().getRoleId() == 1) {
            if (order.getOrderID() != null) {
                Optional<Order> optionalOrder = orderRepo.findById(order.getOrderID());
                if (optionalOrder.isPresent()) {
                    Order oldOrder = optionalOrder.get();
                    if (order.getFood() != null) {
                        oldOrder.setFood(order.getFood());
                    }
                    if (order.getQuantity() != null) {
                        oldOrder.setQuantity(order.getQuantity());
                    }
                    if (order.getStatus() != null && token1.getUser().getRole().getRoleId() == 1) {
                        oldOrder.setStatus(order.getStatus());
                    }
                    orderRepo.save(oldOrder);
                } else {
                    throw new IllegalStateException("No such order exists");
                }
            } else {
                throw new IllegalStateException("Enter Order Id");
            }

        } else {
            throw new IllegalStateException("It's not your Order");
        }
    }
}
