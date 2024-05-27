package com.example.HansungCapstone.Service.Es;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
public class TypoCurrectionService {

    @Value("${SPELLCHECK_API_ID}")
    private String spellcheckApiId;

    @Value("${SPELLCHECK_API_KEY}")
    private String spellcheckApiKey;

    private String apiUrl = "https://openapi.naver.com";

    public String correctTypo(String query) {
        return getCurrectTypo(query);
    }

    private synchronized String getCurrectTypo(String query) {

        return WebClient.builder()
                .baseUrl(apiUrl)
                .build()
                .get()
                .uri("/v1/search/errata.json?query=" + query)
                .header("X-Naver-Client-Id", spellcheckApiId)
                .header("X-Naver-Client-Secret", spellcheckApiKey)
                .retrieve()
                .bodyToMono(Errata.class)
                .retryWhen(Retry.backoff(5, Duration.ofSeconds(2)))
                .onErrorReturn(new Errata(query))
                .block()
                .getErrata();
    }

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    private static class Errata {
        private String errata;
    }
}
