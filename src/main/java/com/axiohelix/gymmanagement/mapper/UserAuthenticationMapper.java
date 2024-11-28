package com.axiohelix.gymmanagement.mapper;

import com.axiohelix.gymmanagement.entity.UserAccount;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserAuthenticationMapper {

    UserAccount selectUserEmailByEmail(String email);
    void create(UserAccount userAccount);
    void update(UserAccount userAccount);
    void deleteUser(String userId);

}
