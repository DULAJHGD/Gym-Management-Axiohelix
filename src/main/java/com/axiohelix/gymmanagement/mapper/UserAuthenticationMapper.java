package com.axiohelix.gymmanagement.mapper;

import com.axiohelix.gymmanagement.entity.UserAccount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserAuthenticationMapper {

    UserAccount selectUserEmailByEmail(String email);
    void create(UserAccount userAccount);
    void update(UserAccount userAccount);
    void deleteUser(String userId);
    List<UserAccount> select();

}
