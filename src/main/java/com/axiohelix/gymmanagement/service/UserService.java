package com.axiohelix.gymmanagement.service;

import com.axiohelix.gymmanagement.entity.UserAccountEntity;
import com.axiohelix.gymmanagement.mapper.UserAuthenticationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserAuthenticationMapper userAuthenticationMapper;

    @Autowired
    public UserService(UserAuthenticationMapper userAuthenticationMapper) {
        this.userAuthenticationMapper = userAuthenticationMapper;
    }

    public Optional<UserAccountEntity> findByEmail(String email) {

        System.out.println("Retrieving user with email: " + email);

        System.out.println("Executing query to fetch user: " + email);
        var user = userAuthenticationMapper.selectUserEmailByEmail(email);
        System.out.println("Retrieved user: " + user);

        if (user == null) {
            System.out.println("No user found with email: " + email);
            return Optional.empty();
        }

        user.setEmail(email);

        System.out.println("User found: " + user.getEmail());

        return Optional.of(user);
    }

    public void insertUser(UserAccountEntity user) {
        userAuthenticationMapper.createUser(user);
    }


    public void updateUser(UserAccountEntity user) {
        userAuthenticationMapper.updateAccount(user);
    }

    public void deleteUser(String userId) {
        userAuthenticationMapper.deleteUser(userId);
    }
}
