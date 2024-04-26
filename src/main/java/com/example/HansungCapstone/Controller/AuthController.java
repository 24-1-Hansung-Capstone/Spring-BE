package com.example.HansungCapstone.Controller;

import com.example.HansungCapstone.DTO.Login.LoginDto;
import com.example.HansungCapstone.DTO.Login.LoginResponseDto;
import com.example.HansungCapstone.DTO.Login.ResponseDto;
import com.example.HansungCapstone.DTO.Login.SignupDto;
import com.example.HansungCapstone.Service.Login.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000",}) // TODO : cors 설정 필요할 수 있음
public class AuthController {
    @Autowired AuthService authService;

    @PostMapping("/signUp")
    public ResponseDto<?> signUp(@RequestBody SignupDto requestBody) {
        ResponseDto<?> result = authService.signUp(requestBody);
        return result;
    }

    @PostMapping("/login")
    public ResponseDto<LoginResponseDto> login(@RequestBody LoginDto requestBody) {
        ResponseDto<LoginResponseDto> result = authService.login(requestBody);
        return result;
    }
}
