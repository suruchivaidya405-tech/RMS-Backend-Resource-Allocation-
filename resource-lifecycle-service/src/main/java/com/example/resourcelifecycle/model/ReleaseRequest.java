package com.example.resourcelifecycle.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "release_request")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReleaseRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "allocation_id", nullable = false)
    private Allocation allocation;

    @Column(name = "requested_release_date", nullable = false)
    private LocalDate requestedReleaseDate;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String reason;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt;
}

