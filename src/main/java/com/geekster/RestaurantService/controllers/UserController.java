package com.geekster.RestaurantService.controllers;

import com.geekster.RestaurantService.dto.SignInInput;
import com.geekster.RestaurantService.dto.SignInOutput;
import com.geekster.RestaurantService.dto.SignUpInput;
import com.geekster.RestaurantService.dto.SignUpOutput;
import com.geekster.RestaurantService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public SignUpOutput signUp(@RequestBody SignUpInput signUpDto){
        return userService.signUp(signUpDto);
    }

    @PostMapping("/signin")
    public SignInOutput signIn(@RequestBody SignInInput signInDto){
        return userService.signIn(signInDto);
    }

}
