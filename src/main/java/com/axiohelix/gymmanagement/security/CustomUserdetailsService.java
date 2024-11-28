package com.axiohelix.gymmanagement.security;

import com.axiohelix.gymmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserdetailsService implements UserDetailsService {

    private final UserService userservice;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Email fr UserEntity: " + username);
        var user = userservice.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found with email: " + username));;
        System.out.println("Email from UserEntity: " + user.getEmail());

        if (user.getRole() == null) {
            throw new IllegalStateException("User has no assigned role");
        }

        return UserPrincipal.builder()
                .userId(user.getUserId())
                .Email(user.getEmail())
                .authorities(List.of(new SimpleGrantedAuthority(user.getRole())))
                .Password(user.getPassword())
                .build();
    }
}
