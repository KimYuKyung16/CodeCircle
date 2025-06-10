package com.algorithmsolutionproject.algorithmsolution.dto.room;

public record StartSolveProblemResponse(
        String message,
        long now,
        Integer duration
) {
}
