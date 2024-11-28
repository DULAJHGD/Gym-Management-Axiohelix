package com.axiohelix.gymmanagement.mapper;

import com.axiohelix.gymmanagement.entity.ShopUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopUserMapper {

    void insertShopUser(ShopUserEntity shopUser);
    ShopUserEntity selectShopUserById(String id);
    List<ShopUserEntity> selectAllShopUsers();
    void updateShopUser(ShopUserEntity shopUser);
    void deleteShopUser(String id);
}
