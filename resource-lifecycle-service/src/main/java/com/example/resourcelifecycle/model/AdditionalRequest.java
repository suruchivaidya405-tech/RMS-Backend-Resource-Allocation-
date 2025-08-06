package com.example.resourcelifecycle.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class AdditionalRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectId;
    private String skillRequired;
    private Integer count;
    private LocalDate neededBy;
    private String status; // PENDING, FULFILLED, CANCELLED
}