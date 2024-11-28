package com.axiohelix.gymmanagement.mapper;

import com.axiohelix.gymmanagement.entity.UserAccountEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserAuthenticationMapper {

    UserAccountEntity selectUserEmailByEmail(String email);
    void createUser(UserAccountEntity userAccount);
    void updateAccount(UserAccountEntity userAccount);
    void deleteUser(String userId);

}
