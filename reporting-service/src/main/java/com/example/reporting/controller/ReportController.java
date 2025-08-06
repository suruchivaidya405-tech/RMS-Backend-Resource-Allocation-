package com.example.reporting.controller;

import com.example.reporting.client.ResourceServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired
    private ResourceServiceClient resourceClient;

    @GetMapping("/allocations-summary")
    public Map<String, Long> getAllocationsSummary() {
        List<Map<String, Object>> allocations = resourceClient.getAllAllocations();

        long activeAllocations = allocations.stream()
                .filter(a -> "ACTIVE".equals(a.get("status")))
                .count();

        Map<String, Long> report = new HashMap<>();
        report.put("totalAllocations", (long) allocations.size());
        report.put("activeAllocations", activeAllocations);

        return report;
    }
}