package com.geekster.RestaurantService.controllers;

import com.geekster.RestaurantService.models.Food;
import com.geekster.RestaurantService.services.FoodService;
import com.geekster.RestaurantService.services.TokenService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food")
public class FoodController {

    @Autowired
    FoodService foodService;

    @Autowired
    TokenService authService;

    @PostMapping()
    public ResponseEntity<String> addFood(@RequestParam String email , @RequestParam String token , @RequestBody List<Food> food){
        HttpStatus status;
        String msg = "";
        if(authService.authenticate(email,token))
        {
            foodService.addFood(food , token);
            msg = "Food added successfully";
            status = HttpStatus.OK;
        }
        else
        {
            msg = "Invalid user";
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<String>(msg , status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletedFood(@PathVariable Long id , @RequestParam String email , @RequestParam String token){
        HttpStatus status;
        String msg = "";
        if(authService.authenticate(email,token))
        {
            foodService.deleteFood(id , token);
            msg = "Food deleted successfully";
            status = HttpStatus.OK;
        }
        else
        {
            msg = "Invalid user";
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<String>(msg , status);
    }

    @GetMapping()
    public List<Food> getAllFood(@Nullable @RequestParam String foodName){
        return foodService.getAllFood(foodName);
    }
}
