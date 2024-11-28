package com.axiohelix.gymmanagement.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Setter
@Getter
public class ShopUserEntity {

    private String id;

    private String storeName;

    private int maxCount;

    private String companyId;

    private String startDate;

    private String endDate;

    private String phoneNumber;

    private String contactPerson;

    private String address;

    private String token;

    private int status;

    private int sortOrder;

    private String createdBy;

    private String createdOn;

    private String lastModifiedBy;

    private String lastModifiedOn;



    // Constructor to auto-generate a 32-character userId
    public ShopUserEntity() {
        this.id = generateShortUUID();
        this.createdOn =getCurrentTimestamp();
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
