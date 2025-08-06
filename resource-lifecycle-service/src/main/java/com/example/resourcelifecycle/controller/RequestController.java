package com.example.resourcelifecycle.controller;

import com.example.resourcelifecycle.model.*;
import com.example.resourcelifecycle.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/requests")
public class RequestController {
    @Autowired
    private RequestService requestService;

    @PostMapping("/release")
    public ReleaseRequest createReleaseRequest(@RequestBody ReleaseRequest request) {
        return requestService.createReleaseRequest(request);
    }

    @PostMapping("/additional")
    public AdditionalRequest createAdditionalRequest(@RequestBody AdditionalRequest request) {
        return requestService.createAdditionalRequest(request);
    }

    @PostMapping("/extension")
    public ExtensionRequest createExtensionRequest(@RequestBody ExtensionRequest request) {
        return requestService.createExtensionRequest(request);
    }
}