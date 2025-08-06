package com.example.resourcelifecycle.repository;

import com.example.resourcelifecycle.model.AdditionalRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AdditionalRequestRepository extends JpaRepository<AdditionalRequest, Long> {
    List<AdditionalRequest> findByProjectId(String projectId);
    List<AdditionalRequest> findByStatus(String status);
}