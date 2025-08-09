package com.example.resourcelifecycle.controller;

import com.example.resourcelifecycle.model.AdditionalRequest;
import com.example.resourcelifecycle.model.ReleaseRequest;
import com.example.resourcelifecycle.model.RequestStatus;
import com.example.resourcelifecycle.repository.AdditionalRequestRepository;
import com.example.resourcelifecycle.repository.ReleaseRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {
    @Autowired
    private ReleaseRequestRepository releaseRequestRepository;

    @Autowired
    private AdditionalRequestRepository additionalRequestRepository;

    // Release Request Endpoints
    @GetMapping("/release")
    public List<ReleaseRequest> getAllReleaseRequests() {
        return releaseRequestRepository.findAll();
    }

    @PostMapping("/release")
    public ReleaseRequest createReleaseRequest(@RequestBody ReleaseRequest releaseRequest) {
        releaseRequest.setCreatedAt(LocalDate.now());
        releaseRequest.setStatus(RequestStatus.valueOf("PENDING"));
        return releaseRequestRepository.save(releaseRequest);
    }

    @GetMapping("/release/{id}")
    public ResponseEntity<ReleaseRequest> getReleaseRequestById(@PathVariable Long id) {
        return releaseRequestRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Additional Request Endpoints
    @GetMapping("/additional")
    public List<AdditionalRequest> getByProjectId(@RequestParam Long projectId) {
        return additionalRequestRepository.findByProjectId(projectId);
    }

    @PostMapping("/additional")
    public AdditionalRequest createAdditionalRequest(@RequestBody AdditionalRequest additionalRequest) {
        additionalRequest.setStatus(AdditionalRequest.Status.PENDING);
        return additionalRequestRepository.save(additionalRequest);
    }

    @GetMapping("/additional/{id}")
    public ResponseEntity<AdditionalRequest> getAdditionalRequestById(@PathVariable Long id) {
        return additionalRequestRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}