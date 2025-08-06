package com.example.resourcelifecycle.controller;

import com.example.resourcelifecycle.model.Approval;
import com.example.resourcelifecycle.service.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/approvals")
public class ApprovalController {
    @Autowired
    private ApprovalService approvalService;

    @PostMapping
    public Approval createApproval(@RequestBody Approval approval) {
        return approvalService.createApproval(approval);
    }
}