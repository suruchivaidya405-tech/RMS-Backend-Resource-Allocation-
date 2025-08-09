package com.example.resourcelifecycle.controller;

import com.example.resourcelifecycle.model.Allocation;
import com.example.resourcelifecycle.repository.AllocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allocations")
public class AllocationController {
    @Autowired
    private AllocationRepository allocationRepository;

    @GetMapping
    public List<Allocation> getAllAllocations() {
        return allocationRepository.findAll();
    }

    @PostMapping
    public Allocation createAllocation(@RequestBody Allocation allocation) {
        return allocationRepository.save(allocation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Allocation> getAllocationById(@PathVariable Long id) {
        return allocationRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Allocation> updateAllocation(@PathVariable Long id, @RequestBody Allocation allocationDetails) {
        return allocationRepository.findById(id)
                .map(allocation -> {
                    allocation.setProjectId(allocationDetails.getProjectId());
                    allocation.setResourceId(allocationDetails.getResourceId());
                    allocation.setStartDate(allocationDetails.getStartDate());
                    allocation.setEndDate(allocationDetails.getEndDate());
                    allocation.setAllocationPercentage(allocationDetails.getAllocationPercentage());
                    allocation.setStatus(allocationDetails.getStatus());
                    return ResponseEntity.ok(allocationRepository.save(allocation));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAllocation(@PathVariable Long id) {
        return allocationRepository.findById(id)
                .map(allocation -> {
                    allocationRepository.delete(allocation);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}