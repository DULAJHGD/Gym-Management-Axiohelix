package com.axiohelix.gymmanagement.controller;

import com.axiohelix.gymmanagement.entity.ShopUserEntity;
import com.axiohelix.gymmanagement.service.ShopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/company")
public class ShopUserController {
    @Autowired
    private ShopUserService shopUserService;

    // Create a new ShopUser
    @PostMapping
    public void createShopUser(@RequestBody ShopUserEntity shopUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserEmail = authentication.getName(); // Assuming `getName()` returns the user ID.
        // Set the createdBy field to the logged-in user's ID
        shopUser.setCreatedBy(loggedInUserEmail);

        shopUserService.insertShopUser(shopUser);
    }

    // Get ShopUser by ID
    @GetMapping("/{id}")
    public ShopUserEntity getShopUserById(@PathVariable String id) {
        return shopUserService.getShopUserById(id);
    }

    // Get all ShopUsers
    @GetMapping
    public List<ShopUserEntity> getAllShopUsers() {
        return shopUserService.getAllShopUsers();
    }

    // Update a ShopUser
    @PutMapping("/{id}")
    public void updateShopUser(@PathVariable String id, @RequestBody ShopUserEntity shopUser) {
        shopUser.setId(id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserEmail = authentication.getName(); // Assuming `getName()` returns the user ID.
        // Set the createdBy field to the logged-in user's ID

        shopUser.setLastModifiedBy(loggedInUserEmail);
        shopUser.setLastModifiedOn(String.valueOf(LocalDateTime.now()));
        shopUserService.updateShopUser(shopUser);
    }

    // Delete a ShopUser
    @DeleteMapping("/{id}")
    public void deleteShopUser(@PathVariable String id) {
        shopUserService.deleteShopUser(id);
    }
}
