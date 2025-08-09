package com.example.resourcelifecycle.repository;

import com.example.resourcelifecycle.model.Fulfillment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FulfillmentRepository extends JpaRepository<Fulfillment, Long> {

    // Find fulfillment by allocation ID
    Optional<Fulfillment> findByAllocationId(Long allocationId);

    // Find fulfillments within a date range
    List<Fulfillment> findByFulfilledDateBetween(LocalDate startDate, LocalDate endDate);

    // Find fulfillments by user who performed the fulfillment
    List<Fulfillment> findByFulfilledBy(String fulfilledBy);

    // Custom query for fulfillments needing follow-up
    @Query("SELECT f FROM Fulfillment f WHERE f.comments LIKE '%follow-up%'")
    List<Fulfillment> findFulfillmentsNeedingFollowUp();

    // Count fulfillments by user
    @Query("SELECT f.fulfilledBy, COUNT(f) FROM Fulfillment f GROUP BY f.fulfilledBy")
    List<Object[]> countFulfillmentsByUser();

    // Native query for complex reporting
    @Query(
            value = "SELECT * FROM fulfillments f " +
                    "WHERE f.fulfilled_date >= CURRENT_DATE - INTERVAL '30 days' " +
                    "ORDER BY f.fulfilled_date DESC",
            nativeQuery = true
    )
    List<Fulfillment> findRecentFulfillments();
}