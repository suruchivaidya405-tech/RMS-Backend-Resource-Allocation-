package com.example.resourcelifecycle.repository;

import com.example.resourcelifecycle.model.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long> {
    // Custom query methods
}