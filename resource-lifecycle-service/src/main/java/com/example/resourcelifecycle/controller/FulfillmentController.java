package com.example.resourcelifecycle.controller;

import com.example.resourcelifecycle.model.Fulfillment;
import com.example.resourcelifecycle.repository.FulfillmentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/fulfillments")
@RequiredArgsConstructor
public class FulfillmentController {
    private final FulfillmentRepository repository;

    @PostMapping
    public ResponseEntity<Fulfillment> create(@Valid @RequestBody Fulfillment fulfillment) {
        fulfillment.setFulfilledDate(LocalDate.now());
        Fulfillment saved = repository.save(fulfillment);
        return ResponseEntity
                .created(URI.create("/api/fulfillments/" + saved.getId()))
                .body(saved);
    }
}