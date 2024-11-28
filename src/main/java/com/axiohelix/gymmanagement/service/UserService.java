package com.axiohelix.gymmanagement.service;

import com.axiohelix.gymmanagement.entity.UserAccount;
import com.axiohelix.gymmanagement.mapper.UserAuthenticationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserAuthenticationMapper userAuthenticationMapper;

    @Autowired
    public UserService(UserAuthenticationMapper userAuthenticationMapper) {
        this.userAuthenticationMapper = userAuthenticationMapper;
    }

    public Optional<UserAccount> findByEmail(String email) {

        var user = userAuthenticationMapper.selectUserEmailByEmail(email);

        if (user == null) {
            System.out.println("No user found with email !");
            return Optional.empty();
        }

        user.setEmail(email);

        System.out.println("User found !");

        return Optional.of(user);
    }

    public void insert(UserAccount user) {
        userAuthenticationMapper.create(user);
    }


    public void update(UserAccount user) {
        userAuthenticationMapper.update(user);
    }

    public void delete(String userId) {
        userAuthenticationMapper.deleteUser(userId);
    }

    public List<UserAccount> select() {
       return userAuthenticationMapper.select();
    }
}
