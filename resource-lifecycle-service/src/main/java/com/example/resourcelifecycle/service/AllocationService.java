package com.example.resourcelifecycle.service;

import com.example.resourcelifecycle.model.Allocation;
import com.example.resourcelifecycle.repository.AllocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationService {
    @Autowired
    private AllocationRepository allocationRepository;

    public Allocation createAllocation(Allocation allocation) {
        return allocationRepository.save(allocation);
    }

    public List<Allocation> getAllAllocations() {
        return allocationRepository.findAll();
    }

    public Allocation getAllocationById(Long id) {
        return allocationRepository.findById(id).orElse(null);
    }

    public Allocation updateAllocation(Long id, Allocation allocationDetails) {
        Allocation allocation = allocationRepository.findById(id).orElse(null);
        if (allocation != null) {
            allocation.setProjectId(allocationDetails.getProjectId());
            allocation.setResourceId(allocationDetails.getResourceId());
            allocation.setStartDate(allocationDetails.getStartDate());
            allocation.setEndDate(allocationDetails.getEndDate());
            allocation.setStatus(allocationDetails.getStatus());
            return allocationRepository.save(allocation);
        }
        return null;
    }

    public void deleteAllocation(Long id) {
        allocationRepository.deleteById(id);
    }
}