package com.example.HansungCapstone.Repository.Flask;

import com.example.HansungCapstone.DTO.Es.EsDtoWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ChatAndSummaryRepository {

    @Value("${flask.url}")
    private String url;

    @Value("${flask.port}")
    private int port;

    @Value("${flask.protocol}")
    private String protocol;

    public String sendSentimentalRequest(String target) {
        WebClient client = WebClient.create(protocol + "://" + url + ":" + port);

        Mono<String> response = client.post()
                .uri("/sentimental")
                .bodyValue(Map.of("target", target))
                .retrieve()
                .bodyToMono(String.class);

        return response.block(); // 동기적으로 응답을 받기 위해 block() 사용 (실제 서비스에서는 이 방식을 사용하지 않는 것이 좋습니다)
    }

    public String sendSummaryRequest(String searchTitle, String searchResult) {
        System.out.println("그래서 나 됨?");
        WebClient client = WebClient.create(protocol + "://" + url + ":" + port);

        Map<String, String> body = new HashMap<>();
        body.put("title", searchTitle);
        body.put("result", searchResult);

        Mono<String> response = client.post()
                .uri("/docSummary")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class);
        var r = response.block();
        System.out.println(r);
        return r; // 동기적으로 응답을 받기 위해 block() 사용 (실제 서비스에서는 이 방식을 사용하지 않는 것이 좋습니다)
    }
}
