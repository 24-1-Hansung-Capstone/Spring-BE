package com.example.HansungCapstone.Repository.Flask;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.http.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Repository
public class ChatAndSummaryRepository {

    @Value("${flask.url}")
    private String url;

    @Value("${flask.port}")
    private int port;

    @Value("${flask.protocol}")
    private String protocol;

    public String sendRequestToExternalServer(String question) {
        WebClient client = WebClient.create(protocol + "://" + url + ":" + port);

        Mono<String> response = client.post()
                .uri("/chat")
                .bodyValue(Map.of("question", question))
                .retrieve()
                .bodyToMono(String.class);

        return response.block(); // 동기적으로 응답을 받기 위해 block() 사용 (실제 서비스에서는 이 방식을 사용하지 않는 것이 좋습니다)
    }
}
