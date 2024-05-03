package com.example.HansungCapstone.DTO.HomesOffice;

public enum Type {
    JEONSE("전세"), MONTH("월세"), SALE("매매"), CONFER("협의");

    private final String value;

    Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
