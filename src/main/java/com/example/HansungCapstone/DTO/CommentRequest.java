package com.example.HansungCapstone.DTO;

import lombok.*;

@Getter @Setter
public class CommentRequest {
    private String writer;
    private String comment;
    private int realtyId;
}
