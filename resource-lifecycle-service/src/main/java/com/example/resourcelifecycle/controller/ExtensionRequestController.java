package com.example.resourcelifecycle.controller;

import com.example.resourcelifecycle.model.ExtensionRequest;
import com.example.resourcelifecycle.repository.ExtensionRequestRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/extension-requests")
@RequiredArgsConstructor
public class ExtensionRequestController {
    private final ExtensionRequestRepository repository;

    @PostMapping
    public ResponseEntity<ExtensionRequest> create(@Valid @RequestBody ExtensionRequest request) {
        ExtensionRequest saved = repository.save(request);
        return ResponseEntity
                .created(URI.create("/api/extension-requests/" + saved.getId()))
                .body(saved);
    }

    @GetMapping("/allocation/{allocationId}")
    public List<ExtensionRequest> getByAllocation(@PathVariable Long allocationId) {
        return repository.findByAllocationId(allocationId);
    }
}