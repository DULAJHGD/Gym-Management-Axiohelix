package com.axiohelix.gymmanagement.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Getter
@Setter
public class MemberData {

    private String id;

    private String EquipmentId;

    private String memberId;

    private String date;

    private String rawData;

    private int status;

    private int sortOrder;

    private String createdBy;

    private String createdOn;

    private String lastModifiedBy;

    private String lastModifiedOn;

    public MemberData() {
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
