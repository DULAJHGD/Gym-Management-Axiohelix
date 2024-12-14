package com.axiohelix.gymmanagement.controller;

import com.axiohelix.gymmanagement.dto.ShopGroupDto;
import com.axiohelix.gymmanagement.entity.ShopGroup;
import com.axiohelix.gymmanagement.service.ShopGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/add-company")
public class ShopGroupController {

    @Autowired
    private ShopGroupService shopGroupService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void addGroup(@RequestBody ShopGroupDto shopGroupDto) {
        ShopGroup shopGroup = new ShopGroup();

        shopGroupService.insertShopGroup(shopGroup);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ShopGroup> getAllGroups() {
        return shopGroupService.getAllShopGroups();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateGroup(@RequestParam String id, @RequestBody ShopGroupDto shopGroupDto) {
        ShopGroup shopGroup = new ShopGroup();
        shopGroupService.updateShopGroup(shopGroup);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteGroup(@RequestBody ShopGroup shopGroup ,@RequestParam String id) {
        shopGroupService.deleteShopGroup(id);
    }

}

