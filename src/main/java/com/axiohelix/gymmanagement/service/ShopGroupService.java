package com.axiohelix.gymmanagement.service;

import com.axiohelix.gymmanagement.entity.ShopGroup;
import com.axiohelix.gymmanagement.mapper.ShopGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopGroupService {

    @Autowired
    private ShopGroupMapper shopGroupMapper;

    public void insertShopGroup(ShopGroup shopGroup) {
        shopGroupMapper.insert(shopGroup);
    }

    public ShopGroup getShopGroupById(String id) {
        return shopGroupMapper.selectById(id);
    }

    public List<ShopGroup> getAllShopGroups() {
        return shopGroupMapper.selectAll();
    }

    public void deleteShopGroup(String id) {
        shopGroupMapper.delete(id);
    }

    public void updateShopGroup(ShopGroup shopGroup) {
        shopGroupMapper.update(shopGroup);
    }
}
