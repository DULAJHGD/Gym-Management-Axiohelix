package com.axiohelix.gymmanagement.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@RequiredArgsConstructor
public class jwtIssuer {
    private final JwtProperties properties;

    public String issue(String userId, String email, List<String> roles) {
        System.out.println("Issuing token for userId: " + userId + ", email: " + email + ", roles: " + roles);
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS)))  // Set expiration
                .withClaim("e", email)  // Set email claim
                .withClaim("a", roles)  // Set roles claim
                .sign(Algorithm.HMAC256(properties.getSecretKey()));  // Sign the token
    }
}
