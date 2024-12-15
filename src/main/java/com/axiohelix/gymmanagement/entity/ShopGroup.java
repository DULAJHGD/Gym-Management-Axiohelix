package com.axiohelix.gymmanagement.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class ShopGroup {

    private String Id;

    private String name;

    private int branches;

    private String GroupId;

    private String companyId;

    private String remark;

    private int status;

    private int sortOrder;

    private String createdBy;

    private String createdOn;

    private String lastModifiedBy;

    private String lastModifiedOn;


    public ShopGroup() {
        this.Id = generateShortUUID();
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
