package com.axiohelix.gymmanagement.mapper;

import com.axiohelix.gymmanagement.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    void insert(User user);
    void update(User user);
    void delete(String id);
    User getUserById(String id);
}
