package com.axiohelix.gymmanagement.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Getter
@Setter
public class User {

    private String id;

    private String userName;

    private String nickname;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String role;

    private String phone;

    private String gender;

    private String birthday;

    private Byte[] profilePicture;

    private String remark;

    private int status;

    private int sortOrder;

    private String createdBy;

    private String createdOn;

    private String lastUpdatedBy;

    private String lastUpdatedOn;

    public User() {
        this.id = generateShortUUID();
        this.createdOn =getCurrentTimestamp();
        this.status = 1;
    }

    private String generateShortUUID() {
        long timestamp = System.currentTimeMillis();
        int randomPart = new Random().nextInt(70000) + 20000;
        return String.valueOf(timestamp) + randomPart;
    }

    private String getCurrentTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
