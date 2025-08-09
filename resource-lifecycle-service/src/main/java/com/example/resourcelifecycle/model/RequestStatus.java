package com.example.resourcelifecycle.model;

import java.util.Arrays;

public enum RequestStatus {
    // Core statuses
    DRAFT,          // Initial creation state
    PENDING,        // Waiting for approval
    APPROVED,       // Officially approved
    REJECTED,       // Request denied
    FULFILLED,      // Completed/processed

    // Extended statuses
    CANCELLED,      // Withdrawn by requester
    UNDER_REVIEW,   // Being evaluated
    REVISION,       // Needs modifications
    EXPIRED,        // Not acted upon in time
    ARCHIVED;       // Historical record

    // Helper methods
    public boolean isActive() {
        return this == DRAFT || this == PENDING || this == UNDER_REVIEW;
    }

    public boolean isTerminal() {
        return this == APPROVED || this == REJECTED || this == CANCELLED || this == EXPIRED;
    }

    public static RequestStatus fromString(String status) {
        try {
            return RequestStatus.valueOf(status.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    String.format("Invalid status '%s'. Valid values are: %s",
                            status, Arrays.toString(values()))
            );
        }
    }
}