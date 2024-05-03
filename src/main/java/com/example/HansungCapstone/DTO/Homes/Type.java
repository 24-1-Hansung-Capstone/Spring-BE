package com.example.HansungCapstone.DTO.Homes;

public enum Type {
    JEONSE(1), MONTH(2), SALE(3), CONFER(4);

    private final int value;

    Type(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
