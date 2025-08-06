package com.example.resourcelifecycle.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class FulfillmentStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String requirementId;
    private String requirementType; // ALLOCATION, RELEASE, ADDITIONAL, EXTENSION
    private String status;
    private String comments;
}