package com.example.resourcelifecycle.repository;

import com.example.resourcelifecycle.model.Approval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval, Long> {
    // Custom query methods
}