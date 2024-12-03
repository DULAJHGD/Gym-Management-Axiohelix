package com.axiohelix.gymmanagement.controller;

import com.axiohelix.gymmanagement.dto.MemberDataDto;
import com.axiohelix.gymmanagement.entity.DecodedBodydata;
import com.axiohelix.gymmanagement.entity.DecodedPressuredata;
import com.axiohelix.gymmanagement.entity.MemberData;
import com.axiohelix.gymmanagement.service.MemberDataService;
import com.axiohelix.gymmanagement.utills.DecodedMemberData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/member/equipment/data")
public class MemberDataController {

    @Autowired
    private MemberDataService memberDataService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void addMemberData(@RequestBody MemberDataDto equipmentData) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserEmail = authentication.getName(); // Assuming `getName()` returns the user ID.
        // Set the createdBy field to the logged-in user's ID

        MemberData memberData = new MemberData();
        memberData.setCreatedBy(loggedInUserEmail);
        memberData.setEquipmentId(equipmentData.getEquipmentId());
        memberData.setMemberId(equipmentData.getMemberId());
        memberData.setDate(equipmentData.getDate());
        memberData.setRawData(equipmentData.getRawData());

        memberDataService.insertMemberData(memberData);
    }

    // Get all ShopUsers
    @GetMapping
    public List<MemberData> getAllShopUsers() {
        return memberDataService.getAllMemberData();
    }

    @GetMapping("/{memberId}")
    public List<DecodedMemberData> getDecodedMemberData(@PathVariable String memberId, @RequestBody MemberData equipmentdata) {
        // Retrieve the list of MemberDataEntity from the service
        List<MemberData> memberDataEntities = memberDataService.getMemberDecodedData(memberId,equipmentdata.getEquipmentId());

        // Decode the raw data for each entity and return as a list of DecodedMemberDataEntity
        return memberDataEntities.stream()
                .map(entity -> {
                    if ("242011578985623142".equals(entity.getEquipmentId())) {
                        return decodedBodyPlannerData(entity);
                    } else {
                        return decodePressureData(entity);
                    }
                })
                .collect(Collectors.toList());
    }

    // Decode rawData logic
    private DecodedMemberData decodePressureData(MemberData entity) {
        String[] rawDataParts = entity.getRawData().split(",");
        if (rawDataParts.length < 10) {
            throw new IllegalArgumentException("Invalid rawData format");
        }

        // Parse and map the rawData to individual fields
        String date = String.join("/", rawDataParts[0], rawDataParts[1], rawDataParts[2]);
        String time = String.join(":", rawDataParts[3], rawDataParts[4]);
        String maxPressure = rawDataParts[7];
        String minPressure = rawDataParts[8];
        String pulse = rawDataParts[9];

        // Populate DecodedMemberDataEntity with decoded values
        DecodedPressuredata decodedEntity = new DecodedPressuredata();
        decodedEntity.setMemberId(entity.getMemberId());
        decodedEntity.setDate(date);
        decodedEntity.setTime(time);
        decodedEntity.setMaxPressure(maxPressure);
        decodedEntity.setMinPressure(minPressure);
        decodedEntity.setPulse(pulse);
        decodedEntity.setEquipmentId(entity.getEquipmentId());

        return (DecodedMemberData) decodedEntity;
    }

    // Decode using processReceivedData logic
    private DecodedMemberData decodedBodyPlannerData(MemberData entity) {
        // Define the expected keys
        Set<String> expectedKeys = new HashSet<>(Arrays.asList("WT", "BI", "BP", "CP", "DP", "EP", "FP", "VF", "WX", "WK", "WL", "WM", "WN",
                "MB", "MC", "ME", "MF", "MG", "BV", "WV", "BL", "BN", "LB", "LC", "NE", "NF",
                "NG", "NH", "LF", "LG", "NM", "NN", "NO", "NP", "LK", "EN", "WA", "HI", "AG",
                "SE", "CT", "OS", "SY", "SM", "SGW", "SI", "OI", "OJ", "EX", "ID"));

        // Process the raw data
        Map<String, String> dataMap = processReceivedData(entity.getRawData(), expectedKeys);

        DecodedBodydata decodedEntity = new DecodedBodydata();
        decodedEntity.setMemberId(entity.getMemberId());
        decodedEntity.setDate(entity.getDate()); // Use the original date
        decodedEntity.setEquipmentId(entity.getEquipmentId());

        // Map specific fields from the dataMap to the decoded entity
        if (dataMap.containsKey("WT")) decodedEntity.setWeight(dataMap.get("WT"));
        if(dataMap.containsKey("HI")) decodedEntity.setHight(dataMap.get("HI"));
        if (dataMap.containsKey("BI")) decodedEntity.setBmi(dataMap.get("BI"));
        if (dataMap.containsKey("BP")) decodedEntity.setBodyFat(dataMap.get("BP"));
        if (dataMap.containsKey("CP")) decodedEntity.setFatRH(dataMap.get("CP"));
        if (dataMap.containsKey("DP")) decodedEntity.setFatLH(dataMap.get("DP"));
        if (dataMap.containsKey("EP")) decodedEntity.setFatRL(dataMap.get("EP"));
        if (dataMap.containsKey("FP")) decodedEntity.setFatLL(dataMap.get("FP"));
        if (dataMap.containsKey("WX")) decodedEntity.setBodySketal(dataMap.get("WX"));
        if (dataMap.containsKey("WM")) decodedEntity.setSketalRL(dataMap.get("WM"));
        if (dataMap.containsKey("WN")) decodedEntity.setSketalLL(dataMap.get("WN"));
        if (dataMap.containsKey("WK")) decodedEntity.setSketalRH(dataMap.get("WM"));
        if (dataMap.containsKey("WL")) decodedEntity.setSketalLH(dataMap.get("WL"));

        return (DecodedMemberData)decodedEntity;
    }

    // Process received data method
    private static Map<String, String> processReceivedData(String receivedData, Set<String> expectedKeys) {
        String pattern = "\\b([A-Z]+)\\s*(-?\\d+\\.?\\d*)\\b";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(receivedData);

        Map<String, String> collectedData = new HashMap<>();

        while (matcher.find()) {
            String key = matcher.group(1).trim();
            String value = matcher.group(2).trim();

            if (expectedKeys.contains(key) && !collectedData.containsKey(key)) {
                collectedData.put(key, value);
            }
        }

        return collectedData;
    }
}
