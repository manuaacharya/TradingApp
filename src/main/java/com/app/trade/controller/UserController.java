package com.app.trade.controller;

import com.app.trade.model.User;
import com.app.trade.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        logger.info("Inside register method -> registring the user [{}]",user);
        return userService.registerUser(user);

    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        logger.info("Inside login method -> login the user [{}]",user);
        return userService.verify(user);
    }
}
