package com.example.HansungCapstone.DTO.HomesOffice;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealtyDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;
    @NotNull
    private String addr;
    @NotNull
    private String writer; // 매물글 작성자
    @NotNull
    private int type;

    private String content;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "realty")
    private List<RealtyComment> realtyComments = new ArrayList<>();


    @Builder
    public RealtyDto(String name, String addr, String writer, int type, String content, List<RealtyComment> realtyComments) {
        this.name = name;
        this.addr = addr;
        this.writer = writer;
        this.type = type;
        this.content = content;
        this.realtyComments = realtyComments;
    }
}
