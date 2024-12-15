package com.axiohelix.gymmanagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Random;

@Getter
@Setter
public class UserAccount {
    private String userId;

    private String email;

    // @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String role;

    private int status;

    private int sortOrder;

    private String createdBy;

    private String createdOn;

    private String lastModifiedBy;

    private String lastModifiedOn;


    public UserAccount() {
        this.userId = generateShortUUID();
        this.createdOn = String.valueOf(LocalDateTime.now()); // Set as LocalDateTime instead of String
        this.status = 1;
    }

    private String generateShortUUID() {
        long timestamp = System.currentTimeMillis();
        int randomPart = new Random().nextInt(90000) + 10000;
        return String.valueOf(timestamp) + randomPart;
    }


}
