package com.example.resourcelifecycle.repository;

import com.example.resourcelifecycle.model.ReleaseRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReleaseRequestRepository extends JpaRepository<ReleaseRequest, Long> {
    List<ReleaseRequest> findByAllocationId(Long allocationId);
    List<ReleaseRequest> findByStatus(String status);
}