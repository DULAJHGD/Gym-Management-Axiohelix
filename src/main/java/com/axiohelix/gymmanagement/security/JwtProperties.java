package com.axiohelix.gymmanagement.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties("security.jwt")
public class JwtProperties {
    private String secretKey;
}
