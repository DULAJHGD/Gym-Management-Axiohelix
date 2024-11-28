package com.axiohelix.gymmanagement.controller;

import com.axiohelix.gymmanagement.dto.ShopUserDto;
import com.axiohelix.gymmanagement.entity.ShopUser;
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
    public void createShopUser(@RequestBody ShopUserDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserEmail = authentication.getName(); // Assuming `getName()` returns the user ID.
        ShopUser shopUser = new ShopUser();

        shopUser.setStoreName(dto.getStoreName());
        shopUser.setMaxCount(dto.getMaxCount());
        shopUser.setAddress(dto.getAddress());
        shopUser.setContactPerson(dto.getContactPerson());
        shopUser.setPhoneNumber(dto.getPhoneNumber());
        shopUser.setStartDate(dto.getStartDate());
        shopUser.setEndDate(dto.getEndDate());

        // Set the createdBy field to the logged-in user's ID
        shopUser.setCreatedBy(loggedInUserEmail);

        shopUserService.insertShopUser(shopUser);
    }

    // Get ShopUser by ID
    @GetMapping("/{id}")
    public ShopUser getShopUserById(@PathVariable String id) {
        return shopUserService.getShopUserById(id);
    }

    // Get all ShopUsers
    @GetMapping
    public List<ShopUser> getAllShopUsers() {
        return shopUserService.getAllShopUsers();
    }

    // Update a ShopUser
    @PutMapping("/{id}")
    public void updateShopUser(@PathVariable String id, @RequestBody ShopUserDto dto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserEmail = authentication.getName(); // Assuming `getName()` returns the user ID.
        // Set the createdBy field to the logged-in user's ID

        ShopUser shopUser = new ShopUser();
        shopUser.setId(id);
        shopUser.setStoreName(dto.getStoreName());
        shopUser.setMaxCount(dto.getMaxCount());
        shopUser.setAddress(dto.getAddress());
        shopUser.setContactPerson(dto.getContactPerson());
        shopUser.setPhoneNumber(dto.getPhoneNumber());
        shopUser.setStartDate(dto.getStartDate());
        shopUser.setEndDate(dto.getEndDate());
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
