package com.example.HansungCapstone.Service.Login;

import com.example.HansungCapstone.DTO.Login.LoginDto;
import com.example.HansungCapstone.DTO.Login.LoginResponseDto;
import com.example.HansungCapstone.DTO.Login.ResponseDto;
import com.example.HansungCapstone.DTO.Login.SignupDto;
import com.example.HansungCapstone.Domain.User;
import com.example.HansungCapstone.Repository.Login.UserRepository;
import com.example.HansungCapstone.Security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired UserRepository userRepository;
    @Autowired TokenProvider tokenProvider;

    public ResponseDto<?> signUp(SignupDto dto) {
        String userEmail = dto.getUserEmail();
        String userPassword = dto.getUserPassword();
        String userPasswordCheck = dto.getUserPasswordCheck();
        User user = new User(dto); // User 생성

        // email 중복 확인
        try {
            if(userRepository.existsById(userEmail)) { // userEmail이 존재하는지 확인 -> 존재 시 true 존재하지 않으면 false 반환
                return ResponseDto.setFailed("이미 존재하는 이메일입니다.");
            }
        } catch (Exception e) {
            return ResponseDto.setFailed("데이터베이스 연결에 실패하였습니다.");
        }

        if(!userPassword.equals(userPasswordCheck)) {
            return ResponseDto.setFailed("패스워드가 일치하지 않습니다!");
        } // userPassword와 userPasswordCheck가 일치하지 않으면 실패문 출력

        try {
            // UserRepository를 이용해서 DB에 Entity 저쟝
            userRepository.save(user);
        } catch (Exception e) {
            ResponseDto.setFailed("데이터베이스 연결에 실패하였습니다.");
        }

        return ResponseDto.setSuccess("회원가입에 성공했습니다!", null);
    }

    public ResponseDto<LoginResponseDto> login(LoginDto dto) {
        String userEmail = dto.getUserEmail();
        String userPassword = dto.getUserPassword();
        try{
        boolean existed = userRepository.existsByUserEmailAndUserPassword(userEmail,userPassword);
        if(!existed) {
            return ResponseDto.setFailed("입력하신 로그인 정보가 존재하지 않습니다.");
        }
        } catch (Exception e) {
            return ResponseDto.setFailed("데이터베이스 연결에 실패하였습니다.");
        }

        User user = null;
        try {
            // 값이 존재하면
            user = userRepository.findById(userEmail).get(); // 사용자 이메일을 가져옴
        } catch (Exception e) {
            return ResponseDto.setFailed("데이터베이스 연결에 실패하였습니다.");
        }
        user.setUserPassword("");

        String token = tokenProvider.createJwt(userEmail);
        int exprTime = 3600000; // 한 시간

        LoginResponseDto loginResponseDto = new LoginResponseDto(token, exprTime, user);
        return ResponseDto.setSuccessData("로그인에 성공하였습니다.", loginResponseDto);
    }
}