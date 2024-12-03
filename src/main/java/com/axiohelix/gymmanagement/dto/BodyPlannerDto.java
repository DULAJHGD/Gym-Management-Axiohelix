package com.axiohelix.gymmanagement.dto;

import com.axiohelix.gymmanagement.entity.MemberData;
import com.axiohelix.gymmanagement.utills.DecodedMemberData;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BodyPlannerDto{
    private String memberId;

    private String date;

    private String EquipmentId;

    private String hight;

    private String weight;

    private String bmi;

    private String bodyFat;

    private String fatLH;

    private String fatRH;

    private String fatLL;

    private String fatRL;

    private String bodySketal;

    private String sketalLH;

    private String sketalRH;

    private String sketalLL;

    private String sketalRL;
}
