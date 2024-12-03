package com.axiohelix.gymmanagement.dto;

import com.axiohelix.gymmanagement.entity.MemberData;
import com.axiohelix.gymmanagement.utills.DecodedMemberData;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDataDto {
    private String EquipmentId;

    private String memberId;

    private String date;

    private String rawData;
}
