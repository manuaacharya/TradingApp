package com.app.trade.service;

import com.app.trade.Exception.UserExceptions;
import com.app.trade.dao.UserDao;
import com.app.trade.model.User;
import com.app.trade.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class UserService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private UserDao userDao;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User registerUser(User user) {
        if(user.getUserName().isEmpty() || user.getEmail().isEmpty()) {
            throw new UserExceptions("User Name and Email can't be null");
        }else if(userDao.checkExistingUser(user.getUserName(),user.getEmail())) {
            throw new UserExceptions("User Already Exists!!");
        }else{
            user.setPassword(encoder.encode(user.getPassword()));
            user.setCreatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss:SSS")));
            userDao.saveUser(user);
        }
        return user;
    }

    public String verify(User user) {
        User existUser = userDao.fetchUserByEmail(user.getEmail());
        user.setUserName(existUser.getUserName());
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUserName());
        } else {
            return "fail";
        }

    }
}
