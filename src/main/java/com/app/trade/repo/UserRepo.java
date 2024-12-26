package com.app.trade.repo;

import com.app.trade.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findByUserName(String username);

    User findByEmail(String email);

}
