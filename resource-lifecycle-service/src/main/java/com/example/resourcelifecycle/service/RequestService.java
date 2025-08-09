package com.example.resourcelifecycle.service;

import com.example.resourcelifecycle.model.*;
import com.example.resourcelifecycle.repository.AdditionalRequestRepository;
import com.example.resourcelifecycle.repository.ExtensionRequestRepository;
import com.example.resourcelifecycle.repository.ReleaseRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;

@Service
@Transactional
public class RequestService {

    private final ReleaseRequestRepository releaseRequestRepository;
    private final AdditionalRequestRepository additionalRequestRepository;
    private final ExtensionRequestRepository extensionRequestRepository;

    public RequestService(
            ReleaseRequestRepository releaseRequestRepository,
            AdditionalRequestRepository additionalRequestRepository,
            ExtensionRequestRepository extensionRequestRepository) {
        this.releaseRequestRepository = releaseRequestRepository;
        this.additionalRequestRepository = additionalRequestRepository;
        this.extensionRequestRepository = extensionRequestRepository;
    }

    // ========== Release Request Methods ==========
    public ReleaseRequest createReleaseRequest(ReleaseRequest request) {
        request.setStatus(RequestStatus.PENDING);
        return releaseRequestRepository.save(request);
    }

    public List<ReleaseRequest> getReleaseRequestsByAllocation(Long allocationId) {
        return releaseRequestRepository.findByAllocationId(allocationId);
    }

    public ReleaseRequest updateReleaseRequestStatus(Long id, RequestStatus status) {
        ReleaseRequest request = releaseRequestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Release request not found with id: " + id));
        request.setStatus(status);
        return releaseRequestRepository.save(request);
    }


    public AdditionalRequest createAdditionalRequest(AdditionalRequest request) {
        request.setStatus(AdditionalRequest.Status.PENDING);
        return additionalRequestRepository.save(request);
    }

    public AdditionalRequest updateAdditionalRequestStatus(Long id, AdditionalRequest.Status status) {
        AdditionalRequest request = additionalRequestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Additional request not found with id: " + id));
        request.setStatus(status);
        return additionalRequestRepository.save(request);
    }

    // ========== Extension Request Methods ==========
    public ExtensionRequest createExtensionRequest(ExtensionRequest request) {
        request.setStatus(RequestStatus.PENDING);
        return extensionRequestRepository.save(request);
    }

    public ExtensionRequest updateExtensionRequestStatus(Long id, RequestStatus status) {
        ExtensionRequest request = extensionRequestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Extension request not found with id: " + id));
        request.setStatus(status);
        return extensionRequestRepository.save(request);
    }

    // ========== Common Query Methods ==========
    public List<ReleaseRequest> getPendingReleaseRequests() {
        return releaseRequestRepository.findByStatus("PENDING");
    }

    public List<AdditionalRequest> getPendingAdditionalRequests() {
        return additionalRequestRepository.findByStatus(AdditionalRequest.Status.PENDING);
    }

    public List<ExtensionRequest> getPendingExtensionRequests() {
        return extensionRequestRepository.findByStatus(RequestStatus.PENDING);
    }

    public List<AdditionalRequest> getAdditionalRequestsByProject(String projectId) {
        return additionalRequestRepository.findByProjectId(Long.valueOf(projectId));
    }

    public List<ExtensionRequest> getExtensionRequestsByAllocation(Long allocationId) {
        return extensionRequestRepository.findByAllocationId(allocationId);
    }

    // ========== Advanced Query Methods ==========
    public List<ExtensionRequest> getExtensionsExpiringSoon() {
        return extensionRequestRepository.findExtensionsExpiringSoon();
    }

    public List<Object[]> getExtensionStatusCounts() {
        return extensionRequestRepository.countExtensionsByStatus();
    }
}