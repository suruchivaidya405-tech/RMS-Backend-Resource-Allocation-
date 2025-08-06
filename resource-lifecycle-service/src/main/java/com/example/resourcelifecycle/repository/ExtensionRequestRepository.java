package com.example.resourcelifecycle.repository;

import com.example.resourcelifecycle.model.ExtensionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ExtensionRequestRepository extends JpaRepository<ExtensionRequest, Long> {
    List<ExtensionRequest> findByAllocationId(Long allocationId);
    List<ExtensionRequest> findByStatus(String status);
}