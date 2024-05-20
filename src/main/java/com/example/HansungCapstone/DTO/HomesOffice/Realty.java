package com.example.HansungCapstone.DTO.HomesOffice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Realty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;
    @NotNull
    private String addr;
    @NotNull
    private int type;

    private String content;

    @Builder
    public Realty(String name, String addr, int type, String content) {
        this.name = name;
        this.addr = addr;
        this.type = type;
        this.content = content;
    }
}
