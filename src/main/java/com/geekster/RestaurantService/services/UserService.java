package com.geekster.RestaurantService.services;

import com.geekster.RestaurantService.dto.SignInInput;
import com.geekster.RestaurantService.dto.SignInOutput;
import com.geekster.RestaurantService.dto.SignUpInput;
import com.geekster.RestaurantService.dto.SignUpOutput;
import com.geekster.RestaurantService.models.AuthenticationToken;
import com.geekster.RestaurantService.models.User;
import com.geekster.RestaurantService.repos.IUserRepo;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    TokenService tokenService;

    @Autowired
    RoleService roleService;



    public SignUpOutput signUp(SignUpInput signUpDto) {
        User user = userRepo.findFirstByEmail(signUpDto.getEmail());

        if(user!=null){
            throw new IllegalStateException("User already exists!!!!...sign in instead");
        }

        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signUpDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if(!(roleService.validateUserRole(signUpDto.getEmail() , signUpDto.getRole()))){
            throw new IllegalStateException("Enter valid Details");
        }

        user = new User(signUpDto.getUserName(), encryptedPassword , signUpDto.getEmail(),
                  signUpDto.getRole());

        userRepo.save(user);

        return new SignUpOutput("Restaurant User registered","Restaurant user account created successfully");



    }

    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        md5.update(userPassword.getBytes());
        byte[] digested = md5.digest();

        String hash = DatatypeConverter.printHexBinary(digested);

        return hash;
    }

    public SignInOutput signIn(SignInInput signInDto) {
        User user = userRepo.findFirstByEmail(signInDto.getEmail());

        if(user==null){
            throw new IllegalStateException("User invalid!!!!...sign up instead");
        }

        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signInDto.getPassword());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }

        //match it with database encrypted password

        boolean isPasswordValid = encryptedPassword.equals(user.getPassword());

        if(!isPasswordValid)
        {
            throw new IllegalStateException("User invalid!!!!...sign up instead");
        }

        AuthenticationToken token = new AuthenticationToken(user);

        tokenService.saveToken(token);

        //set up output response
        return new SignInOutput("Authentication Successful !!!", token.getToken());
    }
}
