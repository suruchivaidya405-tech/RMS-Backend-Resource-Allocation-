package com.example.resourcelifecycle.repository;

import com.example.resourcelifecycle.model.AdditionalRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AdditionalRequestRepository extends JpaRepository<AdditionalRequest, Long> {

    List<AdditionalRequest> findByProjectId(Long projectId);

    List<AdditionalRequest> findByStatus(AdditionalRequest.Status status);

    @Query("SELECT ar FROM AdditionalRequest ar WHERE ar.neededFrom <= CURRENT_DATE AND ar.neededUntil >= CURRENT_DATE")
    List<AdditionalRequest> findActiveRequests();

    @Query("SELECT ar FROM AdditionalRequest ar WHERE ar.projectId = :projectId AND ar.status = 'PENDING'")
    List<AdditionalRequest> findPendingRequestsByProject(@Param("projectId") Long projectId);
}