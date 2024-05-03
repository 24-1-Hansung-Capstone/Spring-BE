package com.example.HansungCapstone.Oauth.infra.oauth.kakao;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "oauth.kakao")
public record KakaoOauthConfig(
        String clientId,
        String redirectUri,
        String clientSecret,
        List<String> scope
) {
}
