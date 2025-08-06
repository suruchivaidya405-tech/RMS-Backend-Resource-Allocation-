package com.example.resourcelifecycle.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Approval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String requestId;
    private String requestType; // RELEASE, ADDITIONAL, EXTENSION
    private String approverId;
    private LocalDateTime approvalDate;
    private String status; // APPROVED, REJECTED
    private String comments;
}