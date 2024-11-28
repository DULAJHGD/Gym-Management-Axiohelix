package com.axiohelix.gymmanagement.mapper;

import com.axiohelix.gymmanagement.entity.ShopUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopUserMapper {

    void insertShopUser(ShopUser shopUser);
    ShopUser selectShopUserById(String id);
    List<ShopUser> selectAllShopUsers();
    void updateShopUser(ShopUser shopUser);
    void deleteShopUser(String id);
}
