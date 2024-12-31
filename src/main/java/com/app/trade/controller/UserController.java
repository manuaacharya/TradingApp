package com.app.trade.controller;

import com.app.trade.model.ApiResponse;
import com.app.trade.model.User;
import com.app.trade.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin("http://localhost:3000/")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody User user) {
        try{
            logger.info("Inside register method -> registring the user [{}]",user);
            User register = userService.registerUser(user);
            return new ResponseEntity<>(new ApiResponse("Registration successful.", register.hashCode()), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse("An error occured while registering the user.!!"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody User user) {
        try {
            String token = userService.verify(user);
            if (token == null) {
                return new ResponseEntity<>(new ApiResponse("User not found.!!"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(new ApiResponse("Login successful.", token), HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(new ApiResponse("User not found.!!"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse("An email or password provided is wrong.!!"), HttpStatus.BAD_GATEWAY);
        }
    }
}
