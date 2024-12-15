package com.axiohelix.gymmanagement.controller;

import com.axiohelix.gymmanagement.dto.UserDto;
import com.axiohelix.gymmanagement.entity.User;
import com.axiohelix.gymmanagement.entity.UserAccount;
import com.axiohelix.gymmanagement.service.UserProfileService;
import com.axiohelix.gymmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class UserController {

    private final UserProfileService userProfileService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder; // Autowire PasswordEncoder

    @Autowired
    public UserController(UserProfileService userProfileService, UserService userService, PasswordEncoder passwordEncoder) {
        this.userProfileService = userProfileService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createUser(@RequestBody UserDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserEmail = authentication.getName();

        User user = new User();


        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setBirthday(dto.getBirthday());
        user.setGender(dto.getGender());
        user.setUserName(dto.getUserName());
        user.setNickname(dto.getNickname());
        user.setPhone(dto.getPhone());
        user.setProfilePicture(dto.getProfilePicture());

        UserAccount userAccount = new UserAccount();
        userAccount.setEmail(user.getEmail());
        String encryptedPassword = passwordEncoder.encode(dto.getPassword());
        userAccount.setPassword(encryptedPassword);
        userAccount.setRole(dto.getRole());
        userAccount.setCreatedBy(loggedInUserEmail);



        userProfileService.insert(user);
        userService.insert(userAccount);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable String id) {
        return userProfileService.findById(id);
    }

}

