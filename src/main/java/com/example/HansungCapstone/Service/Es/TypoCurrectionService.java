package com.example.HansungCapstone.Service.Es;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class TypoCurrectionService {

    @Value("${SPELLCHECK_API_ID}")
    private String spellcheckApiId;

    @Value("${SPELLCHECK_API_KEY}")
    private String spellcheckApiKey;

    private String apiUrl = "https://openapi.naver.com";

    public synchronized String correctTypo(String query) {
        query = query.strip();
        System.out.println("인코딩 전 : " + query);
        query = URLEncoder.encode(query, StandardCharsets.UTF_8);
        System.out.println("인코딩 후 : " + query);
        return getCurrectTypo(query);
    }

    private String getCurrectTypo(String query) {
        WebClient client = WebClient.builder()
                .baseUrl(apiUrl)
                .build();

        return client.get()
                .uri("/v1/search/errata.json?query=" + query)
                .header("X-Naver-Client-Id", spellcheckApiId)
                .header("X-Naver-Client-Secret", spellcheckApiKey)
                .retrieve()
                .bodyToMono(Errata.class)
                .block()
                .getErrata();
    }

    @Getter
    @NoArgsConstructor
    private static class Errata {
        private String errata;

        public void setErrata(String str) {
            if (str.length() == 0) {
                System.out.println("응 없어");
                this.errata = "-1";
            }
        }
    }
}
