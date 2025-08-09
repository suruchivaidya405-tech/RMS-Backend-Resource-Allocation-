/*
package com.example.resourcelifecycle.service;

import com.example.resourcelifecycle.model.ExtensionRequest;
import com.example.resourcelifecycle.model.ExternalExtensionRequest;
import com.example.resourcelifecycle.model.RequestStatus;
import com.example.resourcelifecycle.repository.ExtensionRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ExternalProjectService {

    private final RestTemplate restTemplate;
    private final ExtensionRequestRepository repository;

    @Value("${external.project.system.url}")
    private String externalSystemUrl;

    public ExternalExtensionRequest syncExtensionApproval(Long requestId) {
        ExtensionRequest request = repository.findById(requestId)
                .orElseThrow();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", "${external.system.api-key}");
        headers.setContentType(MediaType.APPLICATION_JSON);

        String body = String.format("""
            {
                "projectExtensionId": "%s",
                "requestId": %d
            }
            """, request.getId(), requestId);

        ResponseEntity<String> response = restTemplate.exchange(
                externalSystemUrl + "/api/extensions/status",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class
        );


        throw new RuntimeException("External system sync failed");
    }
}*/
