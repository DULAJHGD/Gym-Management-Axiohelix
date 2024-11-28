package com.axiohelix.gymmanagement.service;

import com.axiohelix.gymmanagement.entity.ShopUser;
import com.axiohelix.gymmanagement.mapper.ShopUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopUserService {
    @Autowired
    private ShopUserMapper shopUserMapper;

    public void insertShopUser(ShopUser shopUser) {
        shopUserMapper.insertShopUser(shopUser);
    }

    public ShopUser getShopUserById(String id) {
        return shopUserMapper.selectShopUserById(id);
    }

    public List<ShopUser> getAllShopUsers() {
        return shopUserMapper.selectAllShopUsers();
    }

    public void updateShopUser(ShopUser shopUser) {
        shopUserMapper.updateShopUser(shopUser);
    }

    public void deleteShopUser(String id) {
        shopUserMapper.deleteShopUser(id);
    }
}
