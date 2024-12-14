package com.axiohelix.gymmanagement.mapper;

import com.axiohelix.gymmanagement.entity.ShopGroup;

import java.util.List;

public interface ShopGroupMapper {

   void insert(ShopGroup shopGroup);
   ShopGroup selectById (String id);
   List<ShopGroup> selectAll();
   void update(ShopGroup shopGroup);
   void delete(String id);
}
