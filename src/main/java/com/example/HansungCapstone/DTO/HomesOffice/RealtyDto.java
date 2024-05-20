package com.example.HansungCapstone.DTO.HomesOffice;

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
    private int type;
    private String content;

    @Builder
    public RealtyDto(String name, String addr, int type, String content) {
        this.name = name;
        this.addr = addr;
        this.type = type;
        this.content = content;
    }
}
