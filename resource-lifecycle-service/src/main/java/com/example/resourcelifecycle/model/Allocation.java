package com.example.resourcelifecycle.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Allocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectId;
    private String resourceId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status; // ACTIVE, COMPLETED, CANCELLED
}