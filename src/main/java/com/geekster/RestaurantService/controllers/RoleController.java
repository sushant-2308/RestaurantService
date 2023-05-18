package com.geekster.RestaurantService.controllers;

import com.geekster.RestaurantService.models.Role;
import com.geekster.RestaurantService.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/{email}")
    public String addRole( @PathVariable String email , @RequestBody Role role){

        return roleService.addRole(role , email);
    }
}
