package com.axiohelix.gymmanagement.controller;

import com.axiohelix.gymmanagement.dto.UserAccountDto;
import com.axiohelix.gymmanagement.entity.UserAccount;
import com.axiohelix.gymmanagement.model.LoginRequest;
import com.axiohelix.gymmanagement.model.LoginResponce;
import com.axiohelix.gymmanagement.security.UserPrincipal;
import com.axiohelix.gymmanagement.security.jwtIssuer;
import com.axiohelix.gymmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class AuthController {
    private final jwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    private final PasswordEncoder passwordEncoder; // Autowire PasswordEncoder


    @PostMapping("/auth/login")
    public LoginResponce login(@RequestBody LoginRequest request) {

        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var principal = (UserPrincipal) authentication.getPrincipal();

        var roles = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        var token = jwtIssuer.issue(principal.getUserId(), principal.getEmail(), roles);
        return LoginResponce.builder()
                .accessToken(token)
                .build();
    }

    @PostMapping("/user")
    public void CreateUser(@RequestBody UserAccountDto dto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserEmail = authentication.getName(); // Assuming `getName()` returns the user ID.
        UserAccount userAccount = new UserAccount();
        userAccount.setEmail(dto.getEmail());
        userAccount.setRole(dto.getRole());
        // Set the createdBy field to the logged-in user's ID
        userAccount.setCreatedBy(loggedInUserEmail);
        String encryptedPassword = passwordEncoder.encode(dto.getPassword());
        userAccount.setPassword(encryptedPassword);
        userService.insert(userAccount);
    }

    @PutMapping("/user/{id}")
    public void UpdateUser(@PathVariable String id,@RequestBody UserAccountDto dto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserEmail = authentication.getName();

        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(id);
        userAccount.setEmail(dto.getEmail());
        userAccount.setRole(dto.getRole());
        userAccount.setLastModifiedBy(loggedInUserEmail);
        String encryptedPassword = passwordEncoder.encode(dto.getPassword());
        userAccount.setPassword(encryptedPassword);

        userService.update(userAccount);
    }

    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable String userId){
        userService.delete(userId);
    }
}
