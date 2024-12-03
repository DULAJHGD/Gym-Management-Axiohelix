package com.axiohelix.gymmanagement.dto;

import com.axiohelix.gymmanagement.entity.MemberData;
import com.axiohelix.gymmanagement.utills.DecodedMemberData;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PressureDto {
    private String memberId;

    private String date;

    private String time;

    private String maxPressure;

    private String minPressure;

    private String pulse;

    private String EquipmentId;
}
