package com.app.trade.dao;

import com.app.trade.model.User;
import com.app.trade.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private UserRepo userRepo;

    @Transactional
    public User saveUser(User user){
        return userRepo.save(user);
    }

    @Transactional
    public boolean checkExistingUser(String userName, String email) {
        boolean userExists = userRepo.findByUserName(userName) != null && userRepo.findByEmail(email) != null;
        return userExists;
    }

    public User fetchUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
