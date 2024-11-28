package com.axiohelix.gymmanagement.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponce {

    private final String accessToken;
}
