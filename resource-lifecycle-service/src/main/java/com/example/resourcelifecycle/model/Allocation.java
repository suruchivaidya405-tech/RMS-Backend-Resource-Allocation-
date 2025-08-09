package com.example.resourcelifecycle.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "allocations")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Allocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Column(name = "resource_id", nullable = false)
    private Long resourceId;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "allocation_percentage", nullable = false)
    private Integer allocationPercentage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AllocationStatus status;

    @OneToOne(mappedBy = "allocation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Fulfillment fulfillment;

    // Status management enum
    public enum AllocationStatus {
        DRAFT,          // Initial creation state
        ACTIVE,         // Currently in use
        PENDING_END,    // Scheduled for ending
        COMPLETED,      // Successfully ended
        EXTENDED,       // Had timeline extended
        TERMINATED      // Early termination
    }

    // Lifecycle callbacks
    @PrePersist
    protected void onCreate() {
        if (this.status == null) {
            this.status = AllocationStatus.DRAFT;
        }
    }

    // Business logic methods
    public void markActive() {
        if (this.status != AllocationStatus.DRAFT) {
            throw new IllegalStateException("Only DRAFT allocations can be activated");
        }
        this.status = AllocationStatus.ACTIVE;
    }

    public void requestTermination(String reason) {
        this.status = AllocationStatus.PENDING_END;
        // Additional termination logic can be added
    }

    public void extendAllocation(LocalDate newEndDate) {
        if (newEndDate.isBefore(this.endDate)) {
            throw new IllegalArgumentException("New end date must be after current end date");
        }
        this.endDate = newEndDate;
        this.status = AllocationStatus.EXTENDED;
    }
}