package com.example.resourcelifecycle.service;

import com.example.resourcelifecycle.model.Approval;
import com.example.resourcelifecycle.repository.ApprovalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ApprovalService {
    @Autowired
    private ApprovalRepository approvalRepository;

    public Approval createApproval(Approval approval) {
        approval.setApprovalDate(LocalDateTime.now());
        return approvalRepository.save(approval);
    }
}