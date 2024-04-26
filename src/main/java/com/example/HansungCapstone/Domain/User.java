package com.example.HansungCapstone.Domain;

import com.example.HansungCapstone.DTO.Login.SignupDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="User")
@Table(name="User")
public class User {

    @Id
    private String userEmail;

    private String userPassword;
    private String userNickname;
    private String userAddress;

    public User(SignupDto dto) {
        this.userEmail = dto.getUserEmail();
        this.userPassword = dto.getUserPassword();
        this.userNickname = dto.getUserNickName();
        this.userAddress = dto.getUserAddress() + " " + dto.getUserAddressDetail();
    }
}
