package com.axiohelix.gymmanagement.service;

import com.axiohelix.gymmanagement.entity.User;
import com.axiohelix.gymmanagement.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    private final UserMapper userMapper;

    @Autowired
    public UserProfileService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void insert(User user) {
        userMapper.insert(user);
    }

    public void update(User user) {
        userMapper.update(user);
    }

    public void delete(String id) {
        userMapper.delete(id);
    }

    public User findById(String id) {
        return userMapper.getUserById(id);
    }
}
