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

    public List<Allocation> getAllAllocations() {
        return allocationRepository.findAll();
    }

    public Allocation createAllocation(Allocation allocation) {
        return allocationRepository.save(allocation);
    }

    public Allocation getAllocationById(Long id) {
        return allocationRepository.findById(id).orElse(null);
    }

    public Allocation updateAllocation(Long id, Allocation allocationDetails) {
        return allocationRepository.findById(id)
                .map(allocation -> {
                    allocation.setProjectId(allocationDetails.getProjectId());
                    allocation.setResourceId(allocationDetails.getResourceId());
                    allocation.setStartDate(allocationDetails.getStartDate());
                    allocation.setEndDate(allocationDetails.getEndDate());
                    allocation.setAllocationPercentage(allocationDetails.getAllocationPercentage());
                    //allocation.setStatus(allocationDetails.getStatus());
                    return allocationRepository.save(allocation);
                })
                .orElse(null);
    }

    public boolean deleteAllocation(Long id) {
        return allocationRepository.findById(id)
                .map(allocation -> {
                    allocationRepository.delete(allocation);
                    return true;
                })
                .orElse(false);
    }
}