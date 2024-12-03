package com.axiohelix.gymmanagement.entity;

import com.axiohelix.gymmanagement.utills.DecodedMemberData;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DecodedBodydata extends MemberData implements DecodedMemberData {

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
