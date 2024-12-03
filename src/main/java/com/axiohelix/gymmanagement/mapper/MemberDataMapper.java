package com.axiohelix.gymmanagement.mapper;

import com.axiohelix.gymmanagement.entity.MemberData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberDataMapper {

    void insert(MemberData memberData);
    List<MemberData> selectById(String MemberId);
    List<MemberData> selectMemberDataByIdMachineAndDate(String MemberId, String EquipmentId, String date);
    List<MemberData> selectAll();
    List<MemberData> selectMemberDataByIdMachineDecoded(String MemberId, String EquipmentId);
    void update(MemberData memberDataEntity);
    void delete(String id);
}
