package com.example.HansungCapstone.Service.Apply;

import com.example.HansungCapstone.DTO.HouseApply.HouseApply;
import com.example.HansungCapstone.Repository.Apply.HouseDBRepository;
import com.example.HansungCapstone.Repository.Apply.Impl.APTApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class APTApplyService {

    @Autowired
    private APTApplyRepository aptApplyRepository;
    @Autowired
    private HouseDBRepository houseDBRepository;

    //가져온 HouseApply 리스트를 DB에 삽입하는 메소드
    public void saveAppliesToDatabase() throws IOException {
        List<HouseApply> houseApplies = aptApplyRepository.getApplies();

        if (houseApplies == null) {
            System.out.println("No data to save.");
            return;
        }

        for (HouseApply houseApply : houseApplies) {
            houseDBRepository.save(houseApply);
        }

        System.out.println("Data saved successfully.");
    }

    // 저장된 엔티티 조회하는 메서드
    public List<HouseApply> getSavedApplies() {
        return houseDBRepository.findAll();
    }

    // 기본 키를 사용하여 엔티티 조회하는 메서드
    public Optional<HouseApply> getApplyById(String houseNm) {
        return houseDBRepository.findById(houseNm);
    }
}
