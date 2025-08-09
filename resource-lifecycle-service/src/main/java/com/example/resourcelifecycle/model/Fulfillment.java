package com.example.resourcelifecycle.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "fulfillments")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fulfillment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "allocation_id")
    private Allocation allocation;

    @Column(name = "fulfilled_date", nullable = false)
    private LocalDate fulfilledDate;

    @Column(name = "fulfilled_by", nullable = false)
    private String fulfilledBy;

    @Column(columnDefinition = "TEXT")
    private String comments;
}