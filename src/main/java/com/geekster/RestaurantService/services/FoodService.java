package com.geekster.RestaurantService.services;

import com.geekster.RestaurantService.models.AuthenticationToken;
import com.geekster.RestaurantService.models.Food;
import com.geekster.RestaurantService.repos.IFoodRepo;
import com.geekster.RestaurantService.repos.ITokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    @Autowired
    IFoodRepo foodRepo;

    @Autowired
    ITokenRepo tokenRepo;

    public void addFood(List<Food> food, String token) {
        AuthenticationToken token1 = tokenRepo.findFirstByToken(token);
        if(token1.getUser().getRole().getRoleId()==1){
            foodRepo.saveAll(food);
        }

    }

    public List<Food> getAllFood(String foodName) {
        if(foodName==null){
            return foodRepo.findAll();
        }else{

            return foodRepo.findByFoodName(foodName);
        }
    }

    public void deleteFood(Long id, String token) {
        AuthenticationToken token1 = tokenRepo.findFirstByToken(token);
        if(token1.getUser().getRole().getRoleId()==1){
            foodRepo.deleteById(id);
        }
    }
}
