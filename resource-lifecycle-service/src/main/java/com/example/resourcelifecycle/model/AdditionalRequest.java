package com.example.resourcelifecycle.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "additional_requests")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdditionalRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Column(name = "resource_type", nullable = false)
    private String resourceType;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "skills_required")
    private String skillsRequired;

    @Column(name = "needed_from", nullable = false)
    private LocalDate neededFrom;

    @Column(name = "needed_until", nullable = false)
    private LocalDate neededUntil;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String justification;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
        this.status = Status.PENDING; // Default status
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDate.now();
    }

    public enum Status {
        PENDING, APPROVED, REJECTED, CANCELLED
    }
}