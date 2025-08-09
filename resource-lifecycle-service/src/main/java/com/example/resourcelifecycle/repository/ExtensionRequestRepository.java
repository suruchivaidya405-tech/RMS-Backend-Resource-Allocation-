package com.example.resourcelifecycle.repository;

import com.example.resourcelifecycle.model.ExtensionRequest;
import com.example.resourcelifecycle.model.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

/**
 * Repository for ExtensionRequest entities
 * Author: Suruchi
 */
public interface ExtensionRequestRepository extends JpaRepository<ExtensionRequest, Long> {

    // Basic status query
    List<ExtensionRequest> findByStatus(RequestStatus status);

    // Status query with sorting
    List<ExtensionRequest> findByStatusOrderByCreatedAtAsc(RequestStatus status);

    // Find by allocation ID
    List<ExtensionRequest> findByAllocationId(Long allocationId);

    // Find by allocation and status
    List<ExtensionRequest> findByAllocationIdAndStatus(Long allocationId, RequestStatus status);

    // Find pending requests needing approval
    @Query("SELECT e FROM ExtensionRequest e WHERE e.status = 'PENDING' AND e.newEndDate > CURRENT_DATE")
    List<ExtensionRequest> findPendingExtensionsNeedingApproval();

    // Find extensions by date range
    @Query("SELECT e FROM ExtensionRequest e WHERE e.newEndDate BETWEEN :startDate AND :endDate")
    List<ExtensionRequest> findExtensionsByDateRange(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    // Count extensions by status
    @Query("SELECT e.status, COUNT(e) FROM ExtensionRequest e GROUP BY e.status")
    List<Object[]> countExtensionsByStatus();

    // Native query for complex reports
    @Query(value = """
        SELECT er.* FROM extension_requests er
        JOIN allocations a ON er.allocation_id = a.id
        WHERE a.project_id = :projectId
        AND er.status = 'APPROVED'
        """, nativeQuery = true)
    List<ExtensionRequest> findApprovedExtensionsByProject(@Param("projectId") String projectId);

    // Find expiring soon extensions (7 days)
    @Query("SELECT e FROM ExtensionRequest e " +
            "WHERE e.newEndDate BETWEEN CURRENT_DATE AND CURRENT_DATE + 7 " +
            "AND e.status = 'APPROVED'")
    List<ExtensionRequest> findExtensionsExpiringSoon();
}