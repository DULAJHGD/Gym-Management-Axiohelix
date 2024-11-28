package com.axiohelix.gymmanagement.controller;

import com.axiohelix.gymmanagement.entity.UserAccountEntity;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final jwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;


    @PostMapping("/auth/login")
    public LoginResponce login(@RequestBody LoginRequest request) {
        System.out.println("Email: " + request.getEmail());
        System.out.println("Password: " + request.getPassword());

        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var principal = (UserPrincipal) authentication.getPrincipal();
        System.out.println("Principal Email: " + principal.getEmail());

        var roles = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        // Log before calling issue method
        System.out.println("Calling jwtIssuer.issue with userId: " + principal.getUserId() +
                ", email: " + principal.getEmail() + ", roles: " + roles);

        var token = jwtIssuer.issue(principal.getUserId(), principal.getEmail(), roles);
        System.out.println("Principal Email 1: " + principal.getEmail());
        return LoginResponce.builder()
                .accessToken(token)
                .build();
    }

    @PostMapping("/api/user")
    public void CreateUser(@RequestBody UserAccountEntity userAccount){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserEmail = authentication.getName(); // Assuming `getName()` returns the user ID.
        // Set the createdBy field to the logged-in user's ID
        userAccount.setCreatedBy(loggedInUserEmail);
        userService.insertUser(userAccount);
    }

    @PutMapping("/api/user")
    public void UpdateUser(@RequestBody UserAccountEntity userAccount){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserEmail = authentication.getName();

        userAccount.setLastModifiedBy(loggedInUserEmail);

        userService.updateUser(userAccount);
    }

    @DeleteMapping("api/{userId}")
    public void deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
    }
}
