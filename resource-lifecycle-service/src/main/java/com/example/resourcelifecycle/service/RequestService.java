package com.example.resourcelifecycle.service;

import com.example.resourcelifecycle.model.*;
import com.example.resourcelifecycle.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class RequestService {

    private final ReleaseRequestRepository releaseRequestRepository;
    private final AdditionalRequestRepository additionalRequestRepository;
    private final ExtensionRequestRepository extensionRequestRepository;

    // Constructor injection (recommended)
    public RequestService(
            ReleaseRequestRepository releaseRequestRepository,
            AdditionalRequestRepository additionalRequestRepository,
            ExtensionRequestRepository extensionRequestRepository) {
        this.releaseRequestRepository = releaseRequestRepository;
        this.additionalRequestRepository = additionalRequestRepository;
        this.extensionRequestRepository = extensionRequestRepository;
    }

    // Release Request Methods
    public ReleaseRequest createReleaseRequest(ReleaseRequest request) {
        return releaseRequestRepository.save(request);
    }

    public List<ReleaseRequest> getReleaseRequestsByAllocation(Long allocationId) {
        return releaseRequestRepository.findByAllocationId(allocationId);
    }

    // Additional Request Methods
    public AdditionalRequest createAdditionalRequest(AdditionalRequest request) {
        return additionalRequestRepository.save(request);
    }

    public List<AdditionalRequest> getAdditionalRequestsByProject(String projectId) {
        return additionalRequestRepository.findByProjectId(projectId);
    }

    // Extension Request Methods
    public ExtensionRequest createExtensionRequest(ExtensionRequest request) {
        return extensionRequestRepository.save(request);
    }

    public List<ExtensionRequest> getExtensionRequestsByAllocation(Long allocationId) {
        return extensionRequestRepository.findByAllocationId(allocationId);
    }

    // Common Methods
    public List<ReleaseRequest> getPendingReleaseRequests() {
        return releaseRequestRepository.findByStatus("PENDING");
    }

    public List<AdditionalRequest> getPendingAdditionalRequests() {
        return additionalRequestRepository.findByStatus("PENDING");
    }

    public List<ExtensionRequest> getPendingExtensionRequests() {
        return extensionRequestRepository.findByStatus("PENDING");
    }
}