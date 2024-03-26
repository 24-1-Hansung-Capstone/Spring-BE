package com.example.HansungCapstone.Controller;

import lombok.Setter;
import org.apache.http.HttpHost;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {

    @Value("${elasticsearch.url}")
    private String esHost;

    @Value("${elasticsearch.port}")
    private int port;

    @Value("${elasticsearch.protocol}")
    private String scheme;

    @Bean
    public org.elasticsearch.client.RestClient restClient() {
        return org.elasticsearch.client.RestClient.builder(
                new HttpHost(esHost, port, scheme)).build();
    }
}
