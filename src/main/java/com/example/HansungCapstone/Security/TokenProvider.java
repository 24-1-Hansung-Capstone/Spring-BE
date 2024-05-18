package com.example.HansungCapstone.Security;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenProvider {
    private static final String SECURITY_KEY = "jwtsecretkey!@";

    // JWT 생성 메서드
    public String createJwt(String userEmail, int duration) {
        try{
        //Date exprTime = Date.from(Instant.now().plus(1, ChronoUnit.HOURS)); //토큰 만료시간을 현재시간의 1시간 후로 설정
            Instant now = Instant.now();
            Instant exprTime = now.plusSeconds(duration);

            //JWT Claim 설정
            // *Claim 집합 << 내용 설정 (페이로드 설정)
            // subject << "sub", issuer << "iss", expiration time << "exp"

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(userEmail)
                    .issueTime(Date.from(now))
                    .expirationTime(Date.from(exprTime))
                    .build();

            // JWT 서명
            SignedJWT signedJWT = new SignedJWT(
                    new JWSHeader(JWSAlgorithm.HS256), // 헤더 설정
                    claimsSet
            );

            //HMAC 서명을 사용하여 JWT 서명
            JWSSigner signer = new MACSigner(SECURITY_KEY.getBytes()); //서명 설정
            signedJWT.sign(signer);
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECURITY_KEY) //HS512알고리즘, 시크릿 키 이용
                .setSubject(userEmail) //JWT의 제목
                .setIssuedAt(new Date()) //생성날짜
                .setExpiration(exprTime) //만료날짜
                .compact(); //생성
    }

    // JWT 검증 메서드(복호화)
    public String validateJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECURITY_KEY) // token을 SECURITY_KEY를 이용해 파싱
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject(); // token을 파싱해서 토큰 생성할 때 넣은 userEmail을 가져올 수 있다
    }
}