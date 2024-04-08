package com.example.HansungCapstone.DTO.HouseApply.impl;

import com.example.HansungCapstone.DTO.HouseApply.HouseApply;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class APTApply implements HouseApply {
    @Id
    private String HOUSE_NM;  //주택명

    private String PBLANC_URL;  //분양정보 URL
    private String SUBSCRPT_AREA_CODE; //공급지역 코드
    private String HSSPLY_ADRES; //공급위치
    private String TOT_SUPLY_HSHLDCO; //공급 규모
    private String RCEPT_BGNDE; //청약접수 시작일
    private String RCEPT_ENDDE; //청약접수 종료일
}
