package com.example.resourcelifecycle.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "external_extension_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExternalExtensionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Allocation project;

    @Column(name = "requested_by", nullable = false)
    private String requestedBy;

    @Column(name = "current_end_date", nullable = false)
    private LocalDate currentEndDate;

    @Column(name = "proposed_end_date", nullable = false)
    private LocalDate proposedEndDate;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String justification;

    @Column(nullable = false)
    private String externalSystemId; // ID in external system

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApprovalStatus status;

    @Column(name = "last_sync_time")
    private LocalDateTime lastSyncTime;

    public enum ApprovalStatus {
        PENDING_EXTENSION,
        APPROVED,
        REJECTED,
        EXTERNALLY_APPROVED
    }
}