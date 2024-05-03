package com.example.HansungCapstone.DTO.Homes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealtyDto {

    private int id;
    private String name;
    private String addr;
    private Type type;
    private String content;

    @Builder
    public RealtyDto(String name, String addr, Type type) {
        this.name = name;
        this.addr = addr;
        this.type = type;
    }
}
