package com.algorithmsolutionproject.algorithmsolution.dto.problem;

import lombok.Builder;

@Builder
public record RunTestCaseDTO(
        String input,
        String expectedOutput,
        String output,
        String result
) {
}
