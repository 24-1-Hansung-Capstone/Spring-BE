package com.example.HansungCapstone.Controller;

import com.example.HansungCapstone.DTO.Login.LoginDto;
import com.example.HansungCapstone.DTO.Login.LoginResponseDto;
import com.example.HansungCapstone.DTO.Login.ResponseDto;
import com.example.HansungCapstone.DTO.Login.SignupDto;
import com.example.HansungCapstone.Service.Login.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {
        "http://localhost:3000", // 로컬 개발 환경
        "http://13.125.234.8:3000" // 배포환경
})
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<ResponseDto<?>> signUp(@RequestBody SignupDto requestBody) {
        ResponseDto<?> result = authService.signUp(requestBody);
        System.out.println("회원가입 요청 받음:");
        System.out.println("이메일: " + requestBody.getUserEmail());
        System.out.println("비밀번호: " + requestBody.getUserPassword());
        System.out.println(result);

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-content-type-options", "nosniff");

        return ResponseEntity.ok()
                .headers(headers)
                .body(result);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<LoginResponseDto>> login(@RequestBody LoginDto requestBody) {
        ResponseDto<LoginResponseDto> result = authService.login(requestBody);
        System.out.println(result);

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-content-type-options", "nosniff");

        return ResponseEntity.ok()
                .headers(headers)
                .body(result);
    }
}