package com.example.HansungCapstone.DTO.HomesOffice;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="comment")
public class RealtyComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String writer;
    private String comment;


    @ManyToOne
    @JoinColumn(name = "realty_id")
    private Realty realty;

    public void update(String comment) {
        this.comment = comment;
    }
}
