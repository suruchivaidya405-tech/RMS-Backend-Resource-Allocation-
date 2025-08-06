package com.example.reporting.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Map;

@Service
public class ResourceServiceClient {
    private final WebClient webClient;

    public ResourceServiceClient(@Value("${resource.service.url}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public List<Map<String, Object>> getAllAllocations() {
        return webClient.get()
                .uri("/api/allocations")
                .retrieve()
                .bodyToMono(List.class)
                .block();
    }
}