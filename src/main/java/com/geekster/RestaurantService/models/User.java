package com.geekster.RestaurantService.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;

    @NotBlank
    private String password;

    @Email
    @NotBlank
    private String email;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Role role;

    public User(String userName , String password , String email , Role role){
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
    }

}
