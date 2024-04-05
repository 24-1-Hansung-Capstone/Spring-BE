package com.example.HansungCapstone.DTO.HouseApply.impl;

import com.example.HansungCapstone.DTO.HouseApply.HouseApply;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APTApply implements HouseApply {
    private String HOUSE_NM;  //name
    private String PBLANC_URL;  //
    private String SUBSCRPT_AREA_CODE;
    private String HSSPLY_ADRES;
    private String TOT_SUPLY_HSHLDCO;
    private String RCEPT_BGNDE;
    private String RCEPT_ENDDE;
}
