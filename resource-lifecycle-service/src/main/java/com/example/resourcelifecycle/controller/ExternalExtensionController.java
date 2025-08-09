package com.example.resourcelifecycle.controller;

import com.example.resourcelifecycle.model.ExternalExtensionRequest;
import com.example.resourcelifecycle.model.ExtensionRequest;
import com.example.resourcelifecycle.model.RequestStatus;
import com.example.resourcelifecycle.repository.ExtensionRequestRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/external/extensions")
@RequiredArgsConstructor
public class ExternalExtensionController {

    private final ExtensionRequestRepository repository;

    @PostMapping
    public ResponseEntity<ExtensionRequest> createRequest(
            @Valid @RequestBody ExternalExtensionRequest request) {
        // Convert ExternalExtensionRequest to ExtensionRequest if needed
        ExtensionRequest convertedRequest = convertToExtensionRequest(request);
        convertedRequest.setStatus(RequestStatus.PENDING);
        ExtensionRequest saved = repository.save(convertedRequest);
        return ResponseEntity
                .created(URI.create("/api/external/extensions/" + saved.getId()))
                .body(saved);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ExtensionRequest>> getByProject(
            @PathVariable String projectId) {
        List<ExtensionRequest> requests = repository.findApprovedExtensionsByProject(projectId);
        return ResponseEntity.ok(requests);
    }

    // Conversion method if needed
    private ExtensionRequest convertToExtensionRequest(ExternalExtensionRequest externalRequest) {
        ExtensionRequest request = new ExtensionRequest();
        request.setAllocation(externalRequest.getProject());
        // Copy other relevant fields
        return request;
    }
}