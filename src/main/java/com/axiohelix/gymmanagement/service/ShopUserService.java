package com.axiohelix.gymmanagement.service;

import com.axiohelix.gymmanagement.entity.ShopUserEntity;
import com.axiohelix.gymmanagement.mapper.ShopUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopUserService {
    @Autowired
    private ShopUserMapper shopUserMapper;

    public void insertShopUser(ShopUserEntity shopUser) {
        shopUserMapper.insertShopUser(shopUser);
    }

    public ShopUserEntity getShopUserById(String id) {
        return shopUserMapper.selectShopUserById(id);
    }

    public List<ShopUserEntity> getAllShopUsers() {
        return shopUserMapper.selectAllShopUsers();
    }

    public void updateShopUser(ShopUserEntity shopUser) {
        shopUserMapper.updateShopUser(shopUser);
    }

    public void deleteShopUser(String id) {
        shopUserMapper.deleteShopUser(id);
    }
}
