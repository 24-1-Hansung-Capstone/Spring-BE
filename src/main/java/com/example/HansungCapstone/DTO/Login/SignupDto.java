package com.example.HansungCapstone.DTO.Login;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupDto {
        private String userEmail;
        private String userPassword;
        private String userPasswordCheck;
        private String userNickName;
        private String userAddress;
        private String userAddressDetail;
}