package com.axiohelix.gymmanagement.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtPrincipalConverter {

    public UserPrincipal convert(DecodedJWT jwt) {
        String email = jwt.getClaim("e").asString();  // Extract email from claim "e"
        if (email == null) {
            System.out.println("Decoded email from JWT is null");  // Debugging
        }

        return UserPrincipal.builder()
                .userId(String.valueOf(jwt.getSubject()))  // User ID from the subject
                .Email(email)  // Set the email
                .authorities(extractAuthoritiesFromClaim(jwt))  // Extract authorities
                .build();
    }

    private List<SimpleGrantedAuthority> extractAuthoritiesFromClaim(DecodedJWT jwt) {
        var claim = jwt.getClaim("a");  // Extract roles from claim "a"
        if (claim.isNull() || claim.isMissing()) {
            return List.of();
        }
        return claim.asList(String.class).stream()
                .map(SimpleGrantedAuthority::new)  // Convert roles to authorities
                .collect(Collectors.toList());
    }
}
