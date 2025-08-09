package com.example.resourcelifecycle.controller;

import com.example.resourcelifecycle.model.AdditionalRequest;
import com.example.resourcelifecycle.repository.AdditionalRequestRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequestMapping("/api/additional-requests")
@RequiredArgsConstructor
public class AdditionalRequestController {

    private final AdditionalRequestRepository repository;

    @PostMapping
    public ResponseEntity<AdditionalRequest> createRequest(
            @Valid @RequestBody AdditionalRequest request) {

        // Ensure default status if not provided
        if (request.getStatus() == null) {
            request.setStatus(AdditionalRequest.Status.PENDING);
        }

        AdditionalRequest saved = repository.save(request);
        return ResponseEntity
                .created(URI.create("/api/additional-requests/" + saved.getId()))
                .body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdditionalRequest> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}