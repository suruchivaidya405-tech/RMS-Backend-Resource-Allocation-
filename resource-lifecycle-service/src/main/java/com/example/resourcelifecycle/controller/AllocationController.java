package com.example.resourcelifecycle.controller;

import com.example.resourcelifecycle.model.Allocation;
import com.example.resourcelifecycle.service.AllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allocations")
public class AllocationController {
    @Autowired
    private AllocationService allocationService;

    @PostMapping
    public Allocation createAllocation(@RequestBody Allocation allocation) {
        return allocationService.createAllocation(allocation);
    }

    @GetMapping
    public List<Allocation> getAllAllocations() {
        return allocationService.getAllAllocations();
    }

    @GetMapping("/{id}")
    public Allocation getAllocationById(@PathVariable Long id) {
        return allocationService.getAllocationById(id);
    }

    @PutMapping("/{id}")
    public Allocation updateAllocation(@PathVariable Long id, @RequestBody Allocation allocationDetails) {
        return allocationService.updateAllocation(id, allocationDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteAllocation(@PathVariable Long id) {
        allocationService.deleteAllocation(id);
    }
}