package com.example.resourcelifecycle.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class ReleaseRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long allocationId;
    private String reason;
    private LocalDate requestedReleaseDate;
    private String status; // PENDING, APPROVED, REJECTED
}