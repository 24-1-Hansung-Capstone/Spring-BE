package com.example.HansungCapstone.DTO.Login;

import com.example.HansungCapstone.Domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private String token;
    private int experTime;
    private User user;
}
