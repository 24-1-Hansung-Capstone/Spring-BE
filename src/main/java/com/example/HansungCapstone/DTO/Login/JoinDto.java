package com.example.HansungCapstone.DTO.Login;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JoinDto {

        @NotBlank(message = "로그인 아이디가 비어있습니다.")
        private String userId;

        @NotBlank(message = "비밀번호가 비어있습니다.")
        private String password;
        private String passwordCheck;

        @NotBlank(message = "닉네임이 비어있습니다.")
        private String nickname;

        // 비밀번호 암호화 X
        public UserDto toEntity() {
            return UserDto.builder()
                    .userId(this.userId)
                    .password(this.password)
                    .nickname(this.nickname)
                    .build();
        }

        // 비밀번호 암호화
        public UserDto toEntity(String encodedPassword) {
            return UserDto.builder()
                    .userId(this.userId)
                    .password(encodedPassword)
                    .nickname(this.nickname)
                    .build();
        }
}