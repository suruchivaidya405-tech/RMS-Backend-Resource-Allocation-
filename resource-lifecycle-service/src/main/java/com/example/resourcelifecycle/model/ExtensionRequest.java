package com.example.resourcelifecycle.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "extension_request")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExtensionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "allocation_id", nullable = false)
    @NotNull(message = "Allocation reference cannot be null")
    private Allocation allocation;

    @Column(name = "new_end_date", nullable = false)
    @Future(message = "New end date must be in the future")
    private LocalDate newEndDate;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotNull(message = "Reason cannot be null")
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private RequestStatus status = RequestStatus.PENDING;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Builder.Default
    private LocalDate createdAt = LocalDate.now();

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @Version
    private Long version;  // Optimistic locking

    @PrePersist
    protected void onCreate() {
        if (this.status == null) {
            this.status = RequestStatus.PENDING;
        }
        if (this.createdAt == null) {
            this.createdAt = LocalDate.now();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDate.now();
    }


}