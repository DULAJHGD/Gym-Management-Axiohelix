package com.axiohelix.gymmanagement.entity;

import com.axiohelix.gymmanagement.utills.DecodedMemberData;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DecodedPressuredata extends MemberData implements DecodedMemberData {

    private String memberId;

    private String date;

    private String time;

    private String maxPressure;

    private String minPressure;

    private String pulse;

    private String EquipmentId;
}
