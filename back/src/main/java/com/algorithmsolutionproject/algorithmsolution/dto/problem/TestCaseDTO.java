package com.algorithmsolutionproject.algorithmsolution.dto.problem;

public record TestCaseDTO(
        Integer id,
        String input,
        String expectedOutput,
        boolean isSample
) {
}
