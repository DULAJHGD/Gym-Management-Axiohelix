package com.axiohelix.gymmanagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Getter
@Setter
public class UserAccountEntity {
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


    public UserAccountEntity() {
        this.userId = generateShortUUID();
        this.createdOn =getCurrentTimestamp();
    }

    private String generateShortUUID() {
        long timestamp = System.currentTimeMillis();
        int randomPart = new Random().nextInt(90000) + 10000;
        return String.valueOf(timestamp) + randomPart;
    }

    private String getCurrentTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

}
