package com.axiohelix.gymmanagement.service;

import com.axiohelix.gymmanagement.entity.MemberData;
import com.axiohelix.gymmanagement.mapper.MemberDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberDataService {
    @Autowired
    private MemberDataMapper memberDataMapper;

    public void insertMemberData(MemberData memberData) {
        memberDataMapper.insert(memberData);
    }
    public List<MemberData> getMemberDataById(String memberId) {
        return memberDataMapper.selectById(memberId);
    }
    public List<MemberData> getAllMemberData() {
        return memberDataMapper.selectAll();
    }
    public List<MemberData> getMemberSortData(String memberId, String EquipmentId, String date) {
        return memberDataMapper.selectMemberDataByIdMachineAndDate(memberId,EquipmentId,date);
    }

    public List<MemberData> getMemberDecodedData(String memberId,String EquipmentId) {
        return memberDataMapper.selectMemberDataByIdMachineDecoded(memberId,EquipmentId);
    }

    public void updateMemberData(MemberData memberData) {
        memberDataMapper.update(memberData);
    }

    public void deleteMemberData(String Id) {
        memberDataMapper.delete(Id);
    }
}
