package com.example.HansungCapstone.Service.Login;

import com.example.HansungCapstone.DTO.Login.JoinDto;
import com.example.HansungCapstone.DTO.Login.LoginDto;
import com.example.HansungCapstone.DTO.Login.UserDto;
import com.example.HansungCapstone.Repository.Login.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;

    // Spring Security를 사용한 로그인 구현 시 사용
    //BCrypt는 단순히 입력을 1회 해시시키는 것이 아니라 솔트(salt)를 부여하여 여러번 해싱하므로 더 안전하게 암호를 관리할 수 있다.(단방향)
     private final BCryptPasswordEncoder encoder;

    /**
     * loginId 중복 체크
     * 회원가입 기능 구현 시 사용
     * 중복되면 true return
     */
    public boolean checkLoginIdDuplicate(String userId) {
        return userRepository.existsByUserId(userId);
    }

    /**
     * nickname 중복 체크
     * 회원가입 기능 구현 시 사용
     * 중복되면 true return
     */
    public boolean checkNicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    /**
     * 회원가입 기능 1
     * 화면에서 JoinRequest(userId, password, nickname)을 입력받아 UserDto로 변환 후 저장
     * userId, nickname 중복 체크는 Controller에서 진행 => 에러 메세지 출력을 위해
     */
    public void join(JoinDto req) {
        userRepository.save(req.toEntity());
    }

    /**
     * 회원가입 기능 2
     * 화면에서 JoinRequest(userId, password, nickname)을 입력받아 UserDto로 변환 후 저장
     * 회원가입 1과는 달리 비밀번호를 암호화해서 저장
     * userId, nickname 중복 체크는 Controller에서 진행 => 에러 메세지 출력을 위해
     */
    public void join2(JoinDto req) {
        userRepository.save(req.toEntity(encoder.encode(req.getPassword())));
    }

    /**
     *  로그인 기능
     *  화면에서 LoginRequest(userId, password)을 입력받아 userId password가 일치하면 UserDto return
     *  userId 존재하지 않거나 password가 일치하지 않으면 null return
     */
    public UserDto login(LoginDto req) {
        Optional<UserDto> optionalUser = userRepository.findByUserId(req.getUserId());

        // userId와 일치하는 UserDto가 없으면 null return
        if(optionalUser.isEmpty()) {
            return null;
        }

        UserDto user = optionalUser.get();

        // 찾아온 UserDto의 password와 입력된 password가 다르면 null return
        if(!user.getPassword().equals(req.getPassword())) {
            return null;
        }

        return user;
    }

    /**
     * userId(Long)를 입력받아 UserDto를 return 해주는 기능
     * 인증, 인가 시 사용
     * userId가 null이거나(로그인 X) userId로 찾아온 UserDto가 없으면 null return
     * userId로 찾아온 UserDto가 존재하면 UserDto return
     */
    public UserDto getLoginUserById(Long userId) {
        if(userId == null) return null;

        Optional<UserDto> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) return null;

        return optionalUser.get();
    }

    /**
     * userId(String)를 입력받아 UserDto를 return 해주는 기능
     * 인증, 인가 시 사용
     * userId가 null이거나(로그인 X) userId로 찾아온 UserDto가 없으면 null return
     * userId로 찾아온 UserDto가 존재하면 UserDto return
     */
    public UserDto getLoginUserByLoginId(String userId) {
        if(userId == null) return null;

        Optional<UserDto> optionalUser = userRepository.findByUserId(userId);
        if(optionalUser.isEmpty()) return null;

        return optionalUser.get();
    }
}
