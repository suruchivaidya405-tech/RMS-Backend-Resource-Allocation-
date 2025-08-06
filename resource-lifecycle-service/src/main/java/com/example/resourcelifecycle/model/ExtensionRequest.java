package com.example.resourcelifecycle.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class ExtensionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long allocationId;
    private LocalDate newEndDate;
    private String reason;
    private String status; // PENDING, APPROVED, REJECTED
}